package com.vkbot.utils;

import com.vkbot.api.messages.Message;

import java.util.List;

public interface VkDecider {

    List<Object> onText(Message message);

}
