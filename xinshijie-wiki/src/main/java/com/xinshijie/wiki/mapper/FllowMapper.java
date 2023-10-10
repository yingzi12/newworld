package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Fllow;
import com.xinshijie.wiki.dto.FllowDto;
import com.xinshijie.wiki.vo.FllowVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 关注 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface FllowMapper extends BaseMapper<Fllow> {

    /**
     * 查询关注
     */
    List<FllowVo> selectFllowList(FllowDto dto);

    /**
     * 普通方法
     * 分页查询关注
     */
    Page<FllowVo> selectPageFllow(Page<FllowVo> page, @Param("dto") FllowDto dto);

    /**
     * 分页查询关注
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<FllowVo> getPageFllow(Page<FllowVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(FllowDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    FllowVo getInfo(Long id);

    FllowVo getInfoByWidUserid(@Param("wid") Integer wid, @Param("userId") Long userid);

}
