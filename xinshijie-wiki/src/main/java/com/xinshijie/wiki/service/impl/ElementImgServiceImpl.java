package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.ElementImg;
import com.xinshijie.wiki.mapper.ElementImgMapper;
import com.xinshijie.wiki.service.IElementImgService;
import com.xinshijie.wiki.dto.ElementImgDto;
import com.xinshijie.wiki.vo.ElementImgVo;
import com.xinshijie.wiki.vo.WorldChronologyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 元素照片列表  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class ElementImgServiceImpl extends ServiceImpl<ElementImgMapper, ElementImg> implements IElementImgService {

    @Autowired
    private ElementImgMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<ElementImgVo> selectElementImgList(ElementImgDto dto) {
        return mapper.selectListElementImg(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementImgVo> selectPageElementImg(ElementImgDto dto) {
        Page<ElementImgVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return mapper.selectPageElementImg(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<ElementImgVo> getPageElementImg(ElementImgDto dto) {
        Page<ElementImgVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<ElementImgVo> qw = new QueryWrapper<>();
        return mapper.getPageElementImg(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public ElementImg add(ElementImgDto dto) {
        ElementImg value = new ElementImg();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(ElementImgDto dto) {
        return mapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return mapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public ElementImgVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

    @Override
    public Integer delByIdWid(Long id, Integer wid) {
        return mapper.delByIdWid(id,wid);
    }

    @Override
    public ElementImgVo selectByWid(Integer wid) {
        return mapper.selectByWid(wid);
    }
}
