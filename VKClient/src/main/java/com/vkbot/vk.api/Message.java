package com.vkbot.vk.api;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.base.BoolInt;
import com.vk.api.sdk.objects.base.Geo;
import com.vk.api.sdk.objects.messages.Action;
import com.vk.api.sdk.objects.messages.ChatPushSettings;
import com.vk.api.sdk.objects.messages.MessageAttachment;

import java.util.List;

public class Message {

    /**
     * Message ID
     */
    @SerializedName("id")
    private Integer id;

    /**
     * Date when the message has been sent in Unixtime
     */
    @SerializedName("date")
    private Integer date;

    /**
     * Date when the message has been updated in Unixtime
     */
    @SerializedName("update_time")
    private Integer updateTime;

    /**
     * Information whether the message is outcoming
     */
    @SerializedName("out")
    private BoolInt out;

    /**
     * Message author's ID
     */
    @SerializedName("user_id")
    private Integer userId;

    /**
     * Message author's ID
     */
    @SerializedName("from_id")
    private Integer fromId;

    /**
     * ID used for sending messages. It returned only for outgoing messages
     */
    @SerializedName("random_id")
    private Integer randomId;

    /**
     * Is it an important message
     */
    @SerializedName("important")
    private String important;

    /**
     * Is it an deleted message
     */
    @SerializedName("deleted")
    private BoolInt deleted;

    /**
     * Whether the message contains smiles
     */
    @SerializedName("emoji")
    private BoolInt emoji;

    /**
     * Forwarded messages
     */
    @SerializedName("fwd_messages")
    private List<com.vk.api.sdk.objects.messages.Message> fwdMessages;

    /**
     * Information whether the messages is read
     */
    @SerializedName("read_state")
    private BoolInt readState;

    /**
     * Message title or chat title
     */
    @SerializedName("title")
    private String title;

    /**
     * Message text
     */
    @SerializedName("text")
    private String body;

    @SerializedName("attachments")
    private List<MessageAttachment> attachments;

    /**
     * Chat ID
     */
    @SerializedName("chat_id")
    private Integer chatId;

    @SerializedName("chat_active")
    private List<Integer> chatActive;

    /**
     * Push settings for the chat
     */
    @SerializedName("push_settings")
    private ChatPushSettings pushSettings;

    /**
     * Action type
     */
    @SerializedName("action")
    private Action action;

    /**
     * User or email ID has been invited to the chat or kicked from the chat
     */
    @SerializedName("action_mid")
    private Integer actionMid;

    /**
     * Email has been invited or kicked
     */
    @SerializedName("action_email")
    private String actionEmail;

    /**
     * Action text
     */
    @SerializedName("action_text")
    private String actionText;

    /**
     * Chat users number
     */
    @SerializedName("users_count")
    private Integer usersCount;

    /**
     * Chat administrator ID
     */
    @SerializedName("admin_id")
    private Integer adminId;

    /**
     * URL of the preview image with 50px in width
     */
    @SerializedName("photo_50")
    private String photo50;

    /**
     * URL of the preview image with 100px in width
     */
    @SerializedName("photo_100")
    private String photo100;

    /**
     * URL of the preview image with 200px in width
     */
    @SerializedName("photo_200")
    private String photo200;

    @SerializedName("geo")
    private Geo geo;

    public Integer getId() {
        return id;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public BoolInt getOut() {
        return out;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public Integer getRandomId() {
        return randomId;
    }

    public String getImportant() {
        return important;
    }

    public BoolInt getDeleted() {
        return deleted;
    }

    public BoolInt getEmoji() {
        return emoji;
    }

    public List<com.vk.api.sdk.objects.messages.Message> getFwdMessages() {
        return fwdMessages;
    }

    public BoolInt getReadState() {
        return readState;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public List<MessageAttachment> getAttachments() {
        return attachments;
    }

    public Integer getChatId() {
        return chatId;
    }

    public List<Integer> getChatActive() {
        return chatActive;
    }

    public ChatPushSettings getPushSettings() {
        return pushSettings;
    }

    public Action getAction() {
        return action;
    }

    public Integer getActionMid() {
        return actionMid;
    }

    public String getActionEmail() {
        return actionEmail;
    }

    public String getActionText() {
        return actionText;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public String getPhoto50() {
        return photo50;
    }

    public String getPhoto100() {
        return photo100;
    }

    public String getPhoto200() {
        return photo200;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public void setOut(BoolInt out) {
        this.out = out;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public void setRandomId(Integer randomId) {
        this.randomId = randomId;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public void setDeleted(BoolInt deleted) {
        this.deleted = deleted;
    }

    public void setEmoji(BoolInt emoji) {
        this.emoji = emoji;
    }

    public void setFwdMessages(List<com.vk.api.sdk.objects.messages.Message> fwdMessages) {
        this.fwdMessages = fwdMessages;
    }

    public void setReadState(BoolInt readState) {
        this.readState = readState;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAttachments(List<MessageAttachment> attachments) {
        this.attachments = attachments;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public void setChatActive(List<Integer> chatActive) {
        this.chatActive = chatActive;
    }

    public void setPushSettings(ChatPushSettings pushSettings) {
        this.pushSettings = pushSettings;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setActionMid(Integer actionMid) {
        this.actionMid = actionMid;
    }

    public void setActionEmail(String actionEmail) {
        this.actionEmail = actionEmail;
    }

    public void setActionText(String actionText) {
        this.actionText = actionText;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public void setPhoto50(String photo50) {
        this.photo50 = photo50;
    }

    public void setPhoto100(String photo100) {
        this.photo100 = photo100;
    }

    public void setPhoto200(String photo200) {
        this.photo200 = photo200;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    boolean isStickerMessage(){
        return attachments.size() == 1 && attachments.get(0).getSticker() != null;
    }

    boolean isVoiceMessage(){
        return attachments.size() == 1 && attachments.get(0).getAudio() != null;
    }

    boolean isSimpleTextMessage(){
        return attachments.size() == 0 && body.length() > 0;
    }
}
