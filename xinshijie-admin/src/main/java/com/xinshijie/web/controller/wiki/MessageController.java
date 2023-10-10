package com.xinshijie.web.controller.wiki;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 管理员 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "MessageController", description = "信息通知")
@RequestMapping("/wiki/message")
public class MessageController extends BaseController {
    @Autowired
    private IMessageService messageService;

    /**
     * 查询所有
     */
    @PostMapping("/list")
    @Operation(summary = "查询通知", description = "查询通知")
    public Result<List<MessageVo>> list(@RequestBody MessageDto dto) {
        List<MessageVo> values = messageService.selectMessageList(dto);
        return Result.success(values);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询通知员", description = "分页查询通知员")
    public Result<Page<MessageVo>> selectSysRoleList(@RequestBody MessageDto dto) {
        Page<MessageVo> values = messageService.selectPageMessage(dto);
        return Result.success(values);
    }


    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增通知员", description = "新增通知员")
    public Result<Message> add(@RequestBody MessageDto dto) {
        return Result.success(messageService.add(dto));
    }

    /**
     * 根据id修改数据
     */
    @PostMapping("/edit")
    @Operation(summary = "根据id修改数据", description = "根据id修改数据")
    public Result<Integer> edit(@RequestBody MessageDto dto) {
        return Result.success(messageService.edit(dto));
    }

    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(messageService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<MessageVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(messageService.getInfo(id));
    }
}

