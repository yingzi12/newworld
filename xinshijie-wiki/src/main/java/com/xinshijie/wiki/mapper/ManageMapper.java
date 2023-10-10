package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Manage;
import com.xinshijie.wiki.dto.ManageDto;
import com.xinshijie.wiki.dto.ManageFindDto;
import com.xinshijie.wiki.vo.ManageVo;
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
public interface ManageMapper extends BaseMapper<Manage> {

    /**
     * 查询管理员
     */
    List<ManageVo> selectManageList(ManageFindDto dto);

    /**
     * 普通方法
     * 分页查询管理员
     */
    Page<ManageVo> selectPageManage(Page<ManageVo> page, @Param("dto") ManageFindDto dto);

    /**
     * 分页查询管理员
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<ManageVo> getPageManage(Page<ManageVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(ManageDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ManageVo getInfo(Long id);

    /**
     * 删除管理员数
     *
     * @param id 管理员数主键
     * @return 结果
     */
    int deleteManageByIdWid(@Param("id") Long id, @Param("wid") Integer wid);

    ManageVo selectByWidUserId(@Param("wid") Integer wid, @Param("userId") Long userId);

    Long countByWidUserId(@Param("wid") Integer wid, @Param("userId") Long userId);

    Integer updateCount(ManageDto dto);

    Integer updateRank( @Param("id")Integer id, @Param("upgrade")Integer upgrade);

}
