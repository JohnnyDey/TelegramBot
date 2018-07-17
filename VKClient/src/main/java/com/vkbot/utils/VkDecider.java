package com.vkbot.utils;

import com.vk.api.sdk.objects.messages.Message;

import java.util.List;

public interface VkDecider {

    List<Object> onText(Message message);

}
