package com.vkbot.bot;

import com.petersamokhin.bots.sdk.clients.Group;

public class VkBot extends Group {

    public VkBot(Integer id, String access_token) {
        super(id, access_token);
    }

    public VkBot(String access_token) {
        super(access_token);
    }
}
