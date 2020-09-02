package com.liu.animal.app.controller;

import com.alibaba.fastjson.JSON;
import com.liu.animal.app.entity.UserChat;
import com.liu.animal.app.entity.UserInfo;
import com.liu.animal.app.service.UserChatService;
import com.liu.animal.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: WebSocketController
 * @Description: 聊天的
 * @Author: Admin
 * @Date 2020/5/3 11:33
 **/
@RestController
@ServerEndpoint(value = "/webSocketOneToOne/{id}")
@Slf4j
public class WebSocketOneToOne {
    //静态变量，用来记录当前在线的连接数，应该把他设计成线程安全的
    private static int onlineCount;
    //实现服务端与单一客户通信的话，可以使用Map来存放，其中key为用户的标识
    private static Map<String, WebSocketOneToOne> connections = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过他来给客户端发送数据
    private Session session;
    private String fromUserId;
    private String toUserId;
    private String from;
    private String to;
    private String username = null;
    private static ApplicationContext applicationContext;
    private static UserChat userChat = new UserChat();

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    List<UserInfo> userInfoList = new ArrayList<>();

    /**
     * @param id
     * @param session
     * @return void
     * @Author Admin
     * @Description 打开链接
     * @Date 17:19 2020/5/20
     **/
    @OnOpen
    public void onOpen(@PathParam(value = "id") String id, Session session) {
        UserService userService = applicationContext.getBean(UserService.class);
//        this.fromUserId = id;
        this.session = session;
        UserInfo userInfo = userService.findById(id);
//        userInfoList.add(userInfo);
        //用户标识
        username = userInfo.getUsername();
        //添加到map中
        connections.put(username, this);
        addOnlineCount();//在线人数增加
        System.out.println("有新连接加入！新用户：" + username + ",当前在线人数为" + getOnlineCount());
    }

    /**
     * @param
     * @return int
     * @Author Admin
     * @Description 关闭连接调用的方法
     * @Date 14:38 2020/5/3
     **/
    @OnClose
    public void onClose() {
        //从map中移除
        connections.remove(username);
        subOnlineCount();          //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * @param message
     * @param session
     * @return void
     * @Author Admin
     * @Description 接收消息
     * @Date 17:19 2020/5/20
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        UserChatService userChatService = applicationContext.getBean(UserChatService.class);
        UserService userService = applicationContext.getBean(UserService.class);
        JSONObject json = JSONObject.fromObject(message);
        String chatId = null;
        if (json.has("content")) {
            String content = (String) json.get("content");
            userChat.setContent(content);
        }
        if (json.has("from")) {
            fromUserId = (String) json.get("from");
            userChat.setFromUserId(fromUserId);
        }
        if (json.has("to")) {
            toUserId = (String) json.get("to");
            userChat.setToUserId(toUserId);
        }
        if (json.has("chat_id")) {
            chatId = (String) json.get("chat_id");
            userChat.setChatId(chatId);
        }
        if (json.has("read")) {
            String read = json.getString("read");
            userChat.setRead(read);
        }
        userChat.setCreateTime(new Date());
        UserInfo userInfo = userService.findById(toUserId);
        UserInfo userInfo1 = userService.findById(fromUserId);
        userInfoList.add(userInfo);
        userInfoList.add(userInfo1);
        this.to = userInfo.getUsername();
        this.from = userInfo1.getUsername();

        userChat.setFromUserName(from);
        userChat.setToUserName(to);
        userChat.setUserInfoList(userInfoList);
        String msg = JSON.toJSONString(userChat);
//        connections.put(from, this);     //添加到map中
        try {
            userChatService.addInfo(userChat);
            send(msg, username, to, chatId);
        } catch (Exception e) {
            log.error("onMessage 数据插入有错" + e.getMessage());
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.debug("onError 发生错误");
        error.printStackTrace();
    }

    /**
     * @param msg
     * @param from
     * @param to
     * @param socketId
     * @return void
     * @Author Admin
     * @Description 发送消息
     * @Date 17:20 2020/5/20
     **/
    public void send(String msg, String from, String to, String socketId) {
        try {
            //to指定用户
            WebSocketOneToOne con = connections.get(to);
            if (con != null) {
                con.session.getBasicRemote().sendText(msg);
            }
            //from具体用户
            WebSocketOneToOne confrom = connections.get(from);
            if (confrom != null) {
                confrom.session.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            log.error("send 出现问题" + e.getMessage());
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketOneToOne.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketOneToOne.onlineCount--;
    }
}