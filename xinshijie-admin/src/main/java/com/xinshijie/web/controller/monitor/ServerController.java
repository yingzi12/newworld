package com.xinshijie.web.controller.monitor;

import com.xinshijie.common.core.vo.Result;
import com.xinshijie.framework.web.domain.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 *
 * @author xinshijie
 */
@Slf4j
@RestController
@RequestMapping("/monitor/server")
public class ServerController {
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public Result<Server> getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return Result.success(server);
    }
}
