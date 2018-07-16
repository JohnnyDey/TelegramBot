package com.vkbot.vk.api.keyboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Action {

    @SerializedName("type")
    @Expose
    private String type = "text";
    @SerializedName("payload")
    @Expose
    private String payload;
    @SerializedName("label")
    @Expose
    private String label;

    @Deprecated
    public String getType() {
        return type;
    }

    @Deprecated
    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getLabel() {
        return label;
    }

    void setLabel(String label) {
        this.label = label;
    }
}
