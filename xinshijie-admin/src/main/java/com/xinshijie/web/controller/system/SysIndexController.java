package com.xinshijie.web.controller.system;

import com.xinshijie.common.config.XinshijieConfig;
import com.xinshijie.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author xinshijie
 */
@Slf4j
@RestController
public class SysIndexController {
    /**
     * 系统基础配置
     */
    @Autowired
    private XinshijieConfig xinshijieConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", xinshijieConfig.getName(), xinshijieConfig.getVersion());
    }
}
