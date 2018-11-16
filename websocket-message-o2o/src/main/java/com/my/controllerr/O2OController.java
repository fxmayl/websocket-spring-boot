package com.my.controllerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import domain.WebSocketMessage;
import domain.WebSocketResponse;

/**
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2018, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.<BR>
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2018年06月01日 10:53
 */
@Controller
public class O2OController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WebSocketResponse say(WebSocketMessage message) {
        return new WebSocketResponse("Welcome, " + message.getName() + "!");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        String userName = "xiao";
        if (!principal.getName().equals("fang")) {
            userName = "fang";
        }
        messagingTemplate.convertAndSendToUser(userName, "/queue/notifications",
            principal.getName() + "-send:" + msg);
    }
}
