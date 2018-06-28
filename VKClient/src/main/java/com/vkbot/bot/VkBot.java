package com.vkbot.bot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.VkDecider;

import javax.inject.Inject;
import java.util.List;


public class VkBot extends Group {

    private Logger logger = LoggerFactory.getLogger(VkBot.class);

    @Inject
    private VkDecider decider;

    public VkBot(){
        super(null);
    }

    public VkBot(Integer id, String access_token) {
        super(id, access_token);
    }

    VkBot(String access_token) {
        super(access_token);
    }

    void setHandler(){
        this.onSimpleTextMessage(message ->{
            List<String> strings = decider.onText(message);
            strings.forEach(s -> new Message()
                                        .from(this)
                                        .to(message.authorId())
                                        .text(s)
                                        .send());
        });

    }

}
