package com.xinshijie.web.controller.admin;

import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.wiki.domain.Message;
import com.xinshijie.wiki.dto.MessageDto;
import com.xinshijie.wiki.service.IMessageService;
import com.xinshijie.wiki.vo.MessageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Slf4j
@RestController
@Tag(name = "AdminMessageController", description = "后台-信息管理")
@RequestMapping("/admin/message")
public class AdminMessageController extends BaseController {

    @Autowired
    private IMessageService messageService;

    /**
     * 发送信息
     */
    @PostMapping("send")
    public Result<Message> send(MessageDto dto) {
        dto.setCreateId(getUserId());
        dto.setCreateName(getUsername());
        return Result.success(messageService.add(dto));
    }

    /**
     * 查询接收到的信息
     */
    @GetMapping("findtReceived")
    public Result<List<MessageVo>> findFirst() {
        return Result.success(messageService.findReceived(getUserId()));
    }

    /**
     * 查询发送的信息
     */
    @GetMapping("findSend")
    public Result<List<MessageVo>> findSend() {
        return Result.success(messageService.findSend(getUserId()));
    }

    /**
     * 查询某次聊天信息
     */
    @GetMapping("findOnce")
    public Result<List<MessageVo>> findOnce(@RequestParam("mid") Long mid) {
        return Result.success(messageService.findOnce(mid));
    }

    /**
     * 标记信息为已读
     */
    @Operation(summary = "标记信息为已读", description = "标记信息为已读")
    @GetMapping("read")
    public Result<Integer> read(@RequestParam("mid") Long mid) {
        return Result.success(messageService.read(mid));
    }
}
