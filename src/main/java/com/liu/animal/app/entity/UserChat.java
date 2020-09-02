package com.liu.animal.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author Admin
 * @Description 聊天
 * @Date 10:36 2020/5/2
 * @return
 **/

public class UserChat implements Serializable {

  private String id;
  private String fromUserId;
  private String fromUserName;
  private String toUserId;
  private String toUserName;
  private String chatId;
  private String content;
  private String read;
  private String readStr;
  private Date createTime;
  private List<UserInfo> userInfoList;
  private List<UserChat> userChatList;
  private UserChat userChat;
  private UserChat userLastChat;
  private String lastChatContent;
  private String readCount;//未读的数量
  private UserInfo userInfo;

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  public String getReadCount() {
    return readCount;
  }

  public void setReadCount(String readCount) {
    this.readCount = readCount;
  }

  public UserChat getUserLastChat() {
    return userLastChat;
  }

  public void setUserLastChat(UserChat userLastChat) {
    this.userLastChat = userLastChat;
  }

  public String getLastChatContent() {
    return lastChatContent;
  }

  public void setLastChatContent(String lastChatContent) {
    this.lastChatContent = lastChatContent;
  }

  public UserChat getUserChat() {
    return userChat;
  }

  public void setUserChat(UserChat userChat) {
    this.userChat = userChat;
  }

  public List<UserChat> getUserChatList() {
    return userChatList;
  }

  public void setUserChatList(List<UserChat> userChatList) {
    this.userChatList = userChatList;
  }

  public String getFromUserName() {
    return fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  public String getToUserName() {
    return toUserName;
  }

  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  public String getReadStr() {

    return readStr;
  }

  public void setReadStr(String readStr) {
    this.readStr = readStr;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(String fromUserId) {
    this.fromUserId = fromUserId;
  }

  public String getToUserId() {
    return toUserId;
  }

  public void setToUserId(String toUserId) {
    this.toUserId = toUserId;
  }

  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getRead() {
    return read;
  }

  public void setRead(String read) {
    this.read = read;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public List<UserInfo> getUserInfoList() {
    return userInfoList;
  }

  public void setUserInfoList(List<UserInfo> userInfoList) {
    this.userInfoList = userInfoList;
  }
}
