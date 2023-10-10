package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Message;
import com.xinshijie.wiki.dto.MessageDto;
import com.xinshijie.wiki.vo.MessageVo;

import java.util.List;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IMessageService extends IService<Message> {

    /**
     * 查询管理员
     */
    List<MessageVo> selectMessageList(MessageDto dto);

    /**
     * 分页查询。普通方法
     * 查询管理员
     */
    Page<MessageVo> selectPageMessage(MessageDto dto);

    /**
     * 分页查询管理员
     */
    Page<MessageVo> getPageMessage(MessageDto dto);

    /**
     * 新增数据
     */
    Message add(MessageDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(MessageDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    MessageVo getInfo(Long id);

    /**
     * 查询接收到的信息
     *
     * @param userId
     * @return
     */
    List<MessageVo> findReceived(Long userId);

    /**
     * 查询发送的信息
     *
     * @param userId
     * @return
     */
    List<MessageVo> findSend(Long userId);

    /**
     * 查询单次聊天记录
     *
     * @param mid
     * @return
     */
    List<MessageVo> findOnce(Long mid);

    Integer read(Long mid);
}
