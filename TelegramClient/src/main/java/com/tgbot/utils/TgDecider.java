package com.tgbot.utils;

import org.telegram.telegrambots.api.objects.Message;

import java.util.List;

public interface TgDecider {

    List<String> onText(Message message);
}
