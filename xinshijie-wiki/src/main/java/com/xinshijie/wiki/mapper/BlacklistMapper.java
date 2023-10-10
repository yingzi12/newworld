package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Blacklist;
import com.xinshijie.wiki.dto.BlacklistDto;
import com.xinshijie.wiki.vo.BlacklistVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface BlacklistMapper extends BaseMapper<Blacklist> {

    /**
     * 查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     */
    List<BlacklistVo> selectBlacklistList(BlacklistDto dto);

    /**
     * 普通方法
     * 分页查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     */
    Page<BlacklistVo> selectPageBlacklist(Page<BlacklistVo> page, @Param("dto") BlacklistDto dto);

    /**
     * 分页查询世界黑名单，禁止在该世界下发布讨论，评论，修改，创建等操作
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<BlacklistVo> getPageBlacklist(Page<BlacklistVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);


    /**
     * 根据id修改数据
     */
    Integer edit(BlacklistDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    BlacklistVo getInfo(Long id);

}
