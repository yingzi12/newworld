package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Redident;
import com.xinshijie.wiki.dto.RedidentDto;
import com.xinshijie.wiki.vo.RedidentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 居民数 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface RedidentMapper extends BaseMapper<Redident> {

    /**
     * 查询居民数
     */
    List<RedidentVo> selectRedidentList(RedidentDto dto);

    /**
     * 普通方法
     * 分页查询居民数
     */
    Page<RedidentVo> selectPageRedident(Page<RedidentVo> page, @Param("dto") RedidentDto dto);

    /**
     * 分页查询居民数
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<RedidentVo> getPageRedident(Page<RedidentVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    RedidentVo  selectWidUserId(@Param("wid")Integer wid, @Param("userId")Long userId);
    /**
     * 根据id修改数据
     */
    Integer edit(RedidentDto dto);

    /**
     * 删除数据
     */
    Integer delById(Integer id);

    /**
     * 根据id数据
     */
    RedidentVo getInfo(Integer id);
    RedidentVo getInfoByWidCreateId(@Param("wid")Integer wid, @Param("createId")Long createId);


    Integer updateCount(RedidentDto dto);

    Integer updateRank( @Param("id")Integer id, @Param("upgrade")Integer upgrade);

}
