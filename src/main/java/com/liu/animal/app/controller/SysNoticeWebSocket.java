package com.liu.animal.app.controller;

import com.alibaba.fastjson.JSON;
import com.liu.animal.app.entity.SysNotice;
import com.liu.animal.app.entity.UserInfo;
import com.liu.animal.app.service.SysNoticeService;
import com.liu.animal.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * @ClassName: SysNotice
 * @Description: 点赞通知的 webSocket
 * @Author: Admin
 * @Date 2020/5/6 14:21
 **/
@RestController
@CrossOrigin
@ServerEndpoint(value = "/sysNoticeWebSocket/{id}")
@Slf4j
public class SysNoticeWebSocket {
    private int tempId;
    //静态变量，用来记录当前在线的连接数，应该把他设计成线程安全的
    private static int onlineCount;
    //实现服务端与单一客户通信的话，可以使用Map来存放，其中key为用户的标识
    private static Map<String, SysNoticeWebSocket> connections = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过他来给客户端发送数据
    private Session session;
    private String fromUserId;
    private String toUserId;
    private String from;
    private String to;
    private String username = null;
    private static ApplicationContext applicationContext;
    private static SysNotice sysNotice = new SysNotice();

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    List<UserInfo> userInfoList = new ArrayList<>();

    /**
     * @param id
     * @param session
     * @return void
     * @Author Admin
     * @Description 连接
     * @Date 17:13 2020/5/20
     **/
    @OnOpen
    public void onOpen(@PathParam(value = "id") String id, Session session) {
        UserService userService = applicationContext.getBean(UserService.class);
//        this.fromUserId = id;
//        sysNotice.setFromUserId(fromUserId);
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
     * @Date 17:13 2020/5/20
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        UserService userService = applicationContext.getBean(UserService.class);
        SysNoticeService sysNoticeService = applicationContext.getBean(SysNoticeService.class);
        JSONObject json = JSONObject.fromObject(message);
        String articleInfoId = null;
        if (json.has("content")) {
            String content = (String) json.get("content");
            sysNotice.setContent(content);
        }
        if (json.has("from_user_id")) {
            fromUserId = (String) json.get("from_user_id");
            sysNotice.setFromUserId(fromUserId);
        }
        if (json.has("to_user_id")) {
            toUserId = (String) json.get("to_user_id");
            sysNotice.setToUserId(toUserId);
        }
        if (json.has("type")) {
            String type = (String) json.get("type");
            sysNotice.setType(type);
        }
        if (json.has("comment_id")) {
            String commentId = (String) json.get("comment_id");
            sysNotice.setCommentId("1");
        }
        if (json.has("article_info_id")) {
            articleInfoId = (String) json.get("article_info_id");
            sysNotice.setArticleInfoId(articleInfoId);
        }
        if (json.has("is_read")) {
            String read = json.getString("is_read");
            sysNotice.setIsRead(read);
        }
//        if (json.has("create_time")) {
//            String createTime = json.getString("create_time");
//            try {
//                Date date = DateUtils.StringToDate(createTime, "yyyy-MM-dd HH:mm:ss");
//                sysNotice.setCreateTime(date);
//            } catch (ParseException e) {
//                log.error("日期转换异常" + e.getMessage());
//            }
//        }
        UserInfo userInfo1 = userService.findById(fromUserId);
        if (userInfo1 != null) {
            userInfoList.add(userInfo1);
            this.from = userInfo1.getUsername();
        }
        UserInfo userInfo = userService.findById(toUserId);
        if (userInfo != null) {
            userInfoList.add(userInfo);
            this.to = userInfo.getUsername();
        }
        sysNotice.setCreateTime(new Date());
        sysNotice.setFromUserName(from);
        sysNotice.setToUserName(to);
        sysNotice.setUserInfoList(userInfoList);
        String msg = JSON.toJSONString(sysNotice);
        try {
            sysNoticeService.addInfo(sysNotice);
        } catch (Exception e) {
            log.error("onMessage 数据插入有错" + e.getMessage());
        }
        send(msg, username, to, articleInfoId);
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
     * @param articleInfoId
     * @return void
     * @Author Admin
     * @Description 转发给指定角色 并实现对应的逻辑
     * @Date 17:14 2020/5/20
     **/
    public void send(String msg, String from, String to, String articleInfoId) {
        SysNoticeService sysNoticeService = applicationContext.getBean(SysNoticeService.class);
        SysNotice sysNotice = sysNoticeService.findEndNotice();
        sysNoticeService.findById(sysNotice.getId());
        try {
            //to指定用户
            SysNoticeWebSocket con = connections.get(to);
            if (con != null) {
                con.session.getBasicRemote().sendText(msg);
            }
//            //from具体用户
//            SysNoticeWebSocket confrom = connections.get(from);
//            if (confrom != null) {
//                confrom.session.getBasicRemote().sendText(msg);
//            }
        } catch (IOException e) {
            log.error("send 出现问题" + e.getMessage());
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        SysNoticeWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        SysNoticeWebSocket.onlineCount--;
    }
}