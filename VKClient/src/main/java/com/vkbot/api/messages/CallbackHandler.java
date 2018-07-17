package com.vkbot.api.messages;

import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.objects.messages.Message;
import com.vkbot.bot.VkBot;
import com.vkbot.utils.MessageUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CallbackHandler extends CallbackApi {

    @Inject
    private VkBot bot;

    private String callback;

    @Override
    public void messageNew(Integer groupId, Message message) {
        if(MessageUtils.isTextMessage(message)){
            bot.simpleTextMessageHandle(message);
        }
//        if(message.isSimpleTextMessage()){
//            bot.simpleTextMessageHandle(message);
//        } else if(message.isStickerMessage()){
//            stickerHandle(message);
//        } else if(message.isVoiceMessage()){
//            voiceMessageHandle(message);
//        }
        callback = "ok";
    }

    @Override
    public void confirmation(Integer groupId) {
        callback = System.getenv("CALL_BACK_ANSWER");
    }

    public String getCallBack(){
        return callback;
    }
}
