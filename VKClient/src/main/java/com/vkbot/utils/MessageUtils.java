package com.vkbot.utils;

import com.vk.api.sdk.objects.messages.Message;

public class MessageUtils {

    public static boolean isTextMessage(Message message){
        return message.getAttachments() == null || message.getAttachments().size() == 0;
    }
}
