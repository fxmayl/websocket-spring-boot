package com.my.controller;

import com.my.domain.WebSocketMessage;
import com.my.domain.WebSocketResponse;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2018, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.<BR>
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2018年05月31日 15:22
 */
@Controller
public class WebSockController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WebSocketResponse say(WebSocketMessage message) {
        return new WebSocketResponse("Welcome, " + message.getName() + "!");
    }
}
