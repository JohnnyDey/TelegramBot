package com.vkbot.bot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;
import com.vkbot.utils.VkDecider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class VkBot extends Group {

    private Logger logger = LoggerFactory.getLogger(VkBot.class);

    @Inject
    private VkDecider decider;

    public VkBot(){
        super("a3b65d21a8272a23e53b7232f780bd15c0eb773608b2805a1242c2b05dfefb23bb2c5a3e7b2cca09a37e4");
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
