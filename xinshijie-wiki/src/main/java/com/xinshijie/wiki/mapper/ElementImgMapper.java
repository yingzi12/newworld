package com.xinshijie.wiki.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xinshijie.wiki.dto.ElementImgDto;
import com.xinshijie.wiki.vo.ElementImgVo;
import com.xinshijie.wiki.domain.ElementImg;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 元素照片列表 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */

public interface ElementImgMapper extends BaseMapper<ElementImg> {

    /**
    * 查询讨论主题表
    */
    List<ElementImgVo> selectListElementImg(ElementImgDto dto);

    /**
     * 普通方法
     * 分页查询讨论主题表
     */
    Page<ElementImgVo> selectPageElementImg(Page<ElementImgVo> page, @Param("dto") ElementImgDto dto);

    /**
     * 分页查询讨论主题表
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<ElementImgVo> getPageElementImg(Page<ElementImgVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据id修改数据
     */
    Integer edit(ElementImgDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ElementImgVo getInfo(Long id);

    Integer delByIdWid(@Param("id")Long id,@Param("wid")Integer wid);


    ElementImgVo selectByWid(@Param("wid")Integer wid);

}

