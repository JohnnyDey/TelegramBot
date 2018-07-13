package com.vkbot.bot;

import action.NotifyAll;
import action.TimerRemind;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vkbot.utils.VkDecider;
import com.vkbot.vk.api.Group;
import com.vkbot.vk.api.Message;
import jpa.entity.TimerId;
import jpa.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

public class VkBot extends Group {

    private final Logger logger = LoggerFactory.getLogger(VkBot.class);

    @Inject
    private VkDecider decider;

    @Override
    protected void simpleTextMessageHandle(Message message) {
        List<Object> strings = decider.onText(message);
        strings.forEach(o -> {
            if(o instanceof String){
                sendMessage(String.valueOf(o), message.getFromId());
            } else if(o instanceof Integer){
                sendSticker((Integer) o, message.getFromId());
            }

        });
    }

    @Override
    protected void stickerHandle(Message message) {

    }

    @Override
    protected void voiceMessageHandle(Message message) {
//        new Message().from(this).to(message.authorId()).sendVoiceMessage("/home/aleksey/Downloads/1.mp3");
    }


    private void sendMessage(String message, Integer id){
        try {
            apiClient.messages()
                    .send(groupActor)
                    .userId(id)
                    .message(message)
                    .execute();
        } catch (ApiException | ClientException e) {
            logger.error("Exception while sending msg: ", e.getMessage());
        }
    }

    private void sendSticker(Integer stickerId, Integer id){
        try {
            apiClient.messages()
                    .send(groupActor)
                    .stickerId(stickerId)
                    .userId(id)
                    .execute();
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
