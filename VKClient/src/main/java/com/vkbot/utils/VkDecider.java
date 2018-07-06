package com.vkbot.utils;

import com.petersamokhin.bots.sdk.objects.Message;

import java.util.List;

public interface VkDecider {

    List<Object> onText(Message message);

}
