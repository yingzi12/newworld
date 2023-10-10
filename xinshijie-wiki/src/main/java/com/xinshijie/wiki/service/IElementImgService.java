package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ElementImg;
import com.xinshijie.wiki.dto.ElementImgDto;
import com.xinshijie.wiki.vo.ElementImgVo;
import com.xinshijie.wiki.vo.WorldChronologyVo;

import java.util.List;

/**
 * <p>
 * 元素照片列表 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
public interface IElementImgService extends IService<ElementImg> {


    /**
  * 查询信息表
  */
    List<ElementImgVo> selectElementImgList(ElementImgDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<ElementImgVo> selectPageElementImg(ElementImgDto dto);

    /**
     * 分页查询信息表
     */
    Page<ElementImgVo> getPageElementImg(ElementImgDto dto);

    /**
     * 新增数据
     */
    ElementImg add(ElementImgDto id);

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

    Integer delByIdWid(Long id, Integer wid);

    ElementImgVo selectByWid(Integer wid);
}
