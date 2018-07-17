package com.vkbot.utils;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.users.UsersNameCase;
import com.vkbot.api.messages.Message;
import jpa.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PhraseDecider;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VkDeciderImpl extends PhraseDecider implements VkDecider {

    private Logger logger = LoggerFactory.getLogger(VkDeciderImpl.class);

    @Inject @Named("api")
    private VkApiClient apiClient;

    @Inject @Named("actor")
    private GroupActor groupActor;

    public List<Object> onText(Message message){
        User user = userServiceImp.getUserByAppId(Long.valueOf(message.getFromId()), User.AppType.VK.name());
        user = registerUserIfNeed(user, Long.valueOf(message.getFromId()));
        return onText(message.getBody(), user);
    }

    private User registerUserIfNeed(User user, Long id){
        if(user == null){
            user = new User();
            user.setUserName(getUserNameByApi(id));
            user.setAppId(id);
            user.setNotify(true);
            user.setAppType(User.AppType.VK.name());
            userServiceImp.saveUser(user);
        }
        return user;
    }

    private String getUserNameByApi(Long id){
        try {
            return apiClient.users().get(groupActor).nameCase(UsersNameCase.NOMINATIVE).userIds(String.valueOf(id)).execute().get(0).getFirstName();
        } catch (ApiException | ClientException e) {
            logger.error("Exception while getting user name: ", e.getMessage());
            return "";
        }
    }

    private Map<String, String> createUserNameRequest(Long id) {
        Map<String, String> params = new HashMap<>();
        params.put("user_ids", String.valueOf(id));
        params.put("name_case", "Nom");
        return params;
    }

}
