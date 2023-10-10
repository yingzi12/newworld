package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.xinshijie.wiki.domain.Discuss;
import com.xinshijie.wiki.dto.DiscussDto;
import com.xinshijie.wiki.vo.DiscussVo;


/**
 * <p>
 * 讨论主题表 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
public interface DiscussMapper extends BaseMapper<Discuss> {

    /**
     * 查询讨论主题表
     */
    List<DiscussVo> selectDiscussList(DiscussDto dto);

    /**
     * 普通方法
     * 分页查询讨论主题表
     */
    Page<DiscussVo> selectPageDiscuss(Page<DiscussVo> page, @Param("dto") DiscussDto dto);

    /**
     * 分页查询讨论主题表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<DiscussVo> getPageDiscuss(Page<DiscussVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(DiscussDto dto);

    Integer editStatus(DiscussDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    DiscussVo getInfo(Long id);

    Integer updateCount(DiscussDto dto);

    Integer updateCountReply(@Param("id")Long id, @Param("count")Integer count );

}
