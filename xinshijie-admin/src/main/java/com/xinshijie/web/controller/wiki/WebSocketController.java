package com.xinshijie.web.controller.wiki;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/editor")
    @SendTo("/topic/document")
    public String handleEditorChanges(String content) {
        // 处理编辑器变化，广播给所有连接的客户端
        return content;
    }
}

