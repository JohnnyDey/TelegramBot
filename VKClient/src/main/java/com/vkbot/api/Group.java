package com.vkbot.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vk.api.sdk.actions.LongPoll;
import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;
import com.vk.api.sdk.objects.messages.Message;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

public abstract class Group extends CallbackApi {

    @Inject @Named("api")
    protected VkApiClient apiClient;

    private String server;

    private String key;

    private Integer ts;

    private boolean on;

    private LongPoll longPoll;

    @Inject @Named("actor")
    protected GroupActor groupActor;


    @PostConstruct
    public void init() throws ClientException, ApiException {
        GetLongPollServerResponse longPoll = apiClient.groups().getLongPollServer(groupActor).execute();
        server = longPoll.getServer();
        key = longPoll.getKey();
        ts = longPoll.getTs();
    }

    public void startBot(){
        on = false;
        new Thread(() -> {
            if(longPoll == null){
                longPoll = apiClient.longPoll();
            }
            while (on){
                pollLoop();
            }
        }).start();
    }

    public void stopBot(){
        on = false;
    }

    private void pollLoop(){
        GetLongPollEventsResponse execute = null;
        try {
            execute = longPoll.getEvents(server, key, ts).waitTime(5).execute();
        } catch (ApiException | ClientException e) {
            try {
                init();
            } catch (ClientException | ApiException e1) {
                e1.printStackTrace();
            }
        }
        if (execute != null && execute.getUpdates().size() > 0) {
            execute.getUpdates().forEach(this::selectHandler);
            ts = execute.getTs();
        }
    }

    private void selectHandler(JsonObject json){
        Message message = new Gson().fromJson(json.get("object"), Message.class);
//        if(message.isSimpleTextMessage()){
//            simpleTextMessageHandle(message);
//        } else if(message.isStickerMessage()){
//            stickerHandle(message);
//        } else if(message.isVoiceMessage()){
//            voiceMessageHandle(message);
//        }
    }


    public abstract void simpleTextMessageHandle(Message message);

    public abstract void stickerHandle(Message message);

    public abstract void voiceMessageHandle(Message message);

}
