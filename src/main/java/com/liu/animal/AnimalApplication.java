package com.liu.animal;

import com.liu.animal.app.controller.SysNoticeWebSocket;
import com.liu.animal.app.controller.WebSocketOneToOne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class AnimalApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AnimalApplication.class, args);
        WebSocketOneToOne.setApplicationContext(applicationContext);
        SysNoticeWebSocket.setApplicationContext(applicationContext);
    }

}
