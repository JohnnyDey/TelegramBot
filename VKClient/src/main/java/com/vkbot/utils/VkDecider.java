package com.vkbot.utils;

import com.vkbot.vk.api.messages.Message;

import java.util.List;

public interface VkDecider {

    List<Object> onText(Message message);

}
