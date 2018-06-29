package com.vkbot.utils;

import com.petersamokhin.bots.sdk.objects.Message;
import jpa.entity.User;
import utils.PhraseDecider;

import java.util.List;

public interface VkDecider {

    List<String> onText(Message message);

}
