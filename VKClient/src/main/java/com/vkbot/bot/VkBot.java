package com.vkbot.bot;

import action.NotifyAll;
import action.TimerRemind;
import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import com.vkbot.utils.VkDecider;
import jpa.entity.TimerId;
import jpa.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class VkBot extends Group implements Serializable {

    @PostConstruct
    public void post(){
        logger.info("VK bot created");
    }

    @PreDestroy
    public void pre(){
        logger.info("VK bot deleted");
    }

    private Logger logger = LoggerFactory.getLogger(VkBot.class);

    @Inject
    private VkDecider decider;

    public VkBot(){
        super("a3b65d21a8272a23e53b7232f780bd15c0eb773608b2805a1242c2b05dfefb23bb2c5a3e7b2cca09a37e4");
    }

    void setHandler(){
        this.onSimpleTextMessage(message ->{
            List<Object> strings = decider.onText(message);
            strings.forEach(o -> {
                if(o instanceof String){
                    new Message().from(this).to(message.authorId()).text(o).send();
                } else if(o instanceof Integer){
                    new Message().from(this).to(message.authorId()).sticker((Integer)o).send();
                }

            });
        });
    }

    public void timeout(@Observes TimerRemind timerRemind){
        TimerId id = timerRemind.getId();
        logger.info("Send msg: " + id);
        new Message().from(this).to(Math.toIntExact(id.getId())).text(id.getMsg()).send();
    }

    public void notify(@Observes NotifyAll notifyAll){
        for(User user : notifyAll.getUsersToNotify()){
            if(user.getAppType().equals(User.AppType.VK.name())){
                new Message().from(this).to(Math.toIntExact(user.getAppId())).text(notifyAll.getMsg()).send();
                new Message().from(this).to(Math.toIntExact(user.getAppId())).text(notifyAll.getDisclaimer()).send();
            }
        }
    }
}
