package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Message;
import com.xinshijie.wiki.dto.MessageDto;
import com.xinshijie.wiki.vo.MessageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询管理员
     */
    List<MessageVo> selectMessageList(MessageDto dto);

    /**
     * 普通方法
     * 分页查询管理员
     */
    Page<MessageVo> selectPageMessage(Page<MessageVo> page, @Param("dto") MessageDto dto);

    /**
     * 分页查询管理员
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<MessageVo> getPageMessage(Page<MessageVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 新增数据
     */
    Integer add(MessageDto dto);

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

    List<MessageVo> findReceive(Long userid);

    List<MessageVo> findSend(Long userid);

    List<MessageVo> findOnce(Long mid);

    Integer read(Long mid);

}
