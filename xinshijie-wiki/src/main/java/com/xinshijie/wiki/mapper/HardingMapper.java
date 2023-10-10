package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Harding;
import com.xinshijie.wiki.dto.HardingDto;
import com.xinshijie.wiki.vo.HardingVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收藏 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface HardingMapper extends BaseMapper<Harding> {

    /**
     * 查询收藏
     */
    List<HardingVo> selectHardingList(HardingDto dto);

    /**
     * 普通方法
     * 分页查询收藏
     */
    Page<HardingVo> selectPageHarding(Page<HardingVo> page, @Param("dto") HardingDto dto);

    /**
     * 分页查询收藏
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<HardingVo> getPageHarding(Page<HardingVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(HardingDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    HardingVo getInfo(Long id);

    HardingVo getInfoBySidUserid(@Param("sid") Long sid, @Param("userid") Long userid);

}
