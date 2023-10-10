package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.Message;
import com.xinshijie.wiki.dto.MessageDto;
import com.xinshijie.wiki.mapper.MessageMapper;
import com.xinshijie.wiki.service.IMessageService;
import com.xinshijie.wiki.vo.MessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询管理员
     */
    @Override
    public List<MessageVo> selectMessageList(MessageDto dto) {
        return messageMapper.selectMessageList(dto);
    }

    /**
     * 分页查询管理员
     */
    @Override
    public Page<MessageVo> selectPageMessage(MessageDto dto) {
        Page<MessageVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return messageMapper.selectPageMessage(page, dto);
    }

    /**
     * 分页查询管理员
     */
    @Override
    public Page<MessageVo> getPageMessage(MessageDto dto) {
        Page<MessageVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<MessageVo> qw = new QueryWrapper<>();
        return messageMapper.getPageMessage(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Message add(MessageDto dto) {
        Message value = new Message();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        value.setCreateTime(LocalDateTime.now());
        value.setReceiveTime(LocalDateTime.now());
        value.setIsDelete(2);
        value.setIsRead(2);
        messageMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(MessageDto dto) {
        return messageMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return messageMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public MessageVo getInfo(Long id) {
        return messageMapper.getInfo(id);
    }

    @Override
    public List<MessageVo> findReceived(Long userId) {
        return messageMapper.findReceive(userId);
    }

    @Override
    public List<MessageVo> findSend(Long userId) {
        return messageMapper.findSend(userId);
    }

    @Override
    public List<MessageVo> findOnce(Long mid) {
        return messageMapper.findOnce(mid);
    }

    @Override
    public Integer read(Long mid) {
        return null;
    }
}
