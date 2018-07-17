package com.vkbot.api;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import static java.lang.System.getenv;

public class Producer {

    private GroupActor groupActor;
    private VkApiClient apiClient;

    @Produces @Named("api")
    public VkApiClient getApiClient(){
        if(apiClient == null){
            TransportClient transportClient = HttpTransportClient.getInstance();
            apiClient = new VkApiClient(transportClient);
        }
        return apiClient;
    }

    @Produces @Named("actor")
    public GroupActor getGroupActor(){
        if(groupActor == null){
            groupActor = new GroupActor(Integer.parseInt(getenv("VK_GROUP_ID")), getenv("VK_BOT_TOKEN"));
        }
        return groupActor;
    }


}
