package com.vkbot.api.messages;

import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.objects.messages.Message;

public class CallbackHandler extends CallbackApi {


    @Override
    public void messageNew(Integer groupId, Message message) {
        super.messageNew(groupId, message);
    }


}
