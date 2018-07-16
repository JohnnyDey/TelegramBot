package com.vkbot.bot;

import action.NotifyAll;
import action.TimerRemind;
import com.google.gson.Gson;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.messages.MessagesSendQuery;
import com.vkbot.utils.VkDecider;
import com.vkbot.vk.api.Group;
import com.vkbot.vk.api.keyboard.Keyboard;
import com.vkbot.vk.api.keyboard.KeyboardMatcher;
import com.vkbot.vk.api.messages.Message;
import jpa.entity.TimerId;
import jpa.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.KeyboardMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class VkBot extends Group {

    private final Logger logger = LoggerFactory.getLogger(VkBot.class);

    @Inject
    private VkDecider decider;

    private Keyboard keyboard;

    @Override
    protected void simpleTextMessageHandle(Message message) {
        List<Object> messages = decider.onText(message);
        Iterator<Object> iterator = messages.iterator();
        do {
            if(iterator.hasNext()){
                Object msg = iterator.next();
                if(msg instanceof String){
                    sendMessage(String.valueOf(msg), message.getFromId());
                } else if(msg instanceof Integer){
                    sendSticker((Integer) msg, message.getFromId());
                }else if(msg instanceof KeyboardMap){
                    setKeyboard((KeyboardMap) msg);
                }
            }else break;
        }while (true);
        keyboard = null;
    }

    @Override
    protected void stickerHandle(Message message) {

    }

    @Override
    protected void voiceMessageHandle(Message message) {
//        new Message().from(this).to(message.authorId()).sendVoiceMessage("/home/aleksey/Downloads/1.mp3");
    }

    private void setKeyboard(KeyboardMap keyboardMap){
        keyboard = KeyboardMatcher.match(keyboardMap);
    }


    private void sendMessage(String message, Integer id){
        try {
            MessagesSendQuery sendQuery = apiClient.messages()
                    .send(groupActor)
                    .userId(id)
                    .message(message);
            if(keyboard != null) {
                sendQuery.unsafeParam("keyboard", new Gson().toJson(keyboard));
            }
             sendQuery.execute();
        } catch (ApiException | ClientException e) {
            logger.error("Exception while sending msg: ", e.getMessage());
        }
    }

    private void sendSticker(Integer stickerId, Integer id){
        try {
            MessagesSendQuery sendQuery = apiClient.messages()
                    .send(groupActor)
                    .userId(id)
                    .stickerId(stickerId);
            if(keyboard != null) {
                sendQuery.unsafeParam("keyboard", new Gson().toJson(keyboard));
            }
            sendQuery.execute();
        } catch (ApiException | ClientException e) {
            logger.error("Exception while sending sticker: ", e.getMessage());
        }
    }


    @PostConstruct
    public void post(){
        logger.info("VK bot created");
    }

    @PreDestroy
    public void pre(){
        logger.info("VK bot deleted");
    }

    public void timeout(@Observes TimerRemind timerRemind){
        TimerId id = timerRemind.getId();
        logger.info("Send msg: " + id);
        sendMessage(id.getMsg(), Math.toIntExact(id.getId()));
    }

    public void notify(@Observes NotifyAll notifyAll){
        for(User user : notifyAll.getUsersToNotify()){
            if(user.getAppType().equals(User.AppType.VK.name())){
                sendMessage(notifyAll.getMsg(), Math.toIntExact(user.getAppId()));
                sendMessage(notifyAll.getDisclaimer(), Math.toIntExact(user.getAppId()));
            }
        }
    }
}
