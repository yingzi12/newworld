package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.LookingBooks;
import com.xinshijie.wiki.mapper.LookingBooksMapper;
import com.xinshijie.wiki.service.ILookingBooksService;
import com.xinshijie.wiki.dto.LookingBooksDto;
import com.xinshijie.wiki.vo.LookingBooksVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 寻找书  服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@Service
public class LookingBooksServiceImpl extends ServiceImpl<LookingBooksMapper, LookingBooks> implements ILookingBooksService {

    @Autowired
    private LookingBooksMapper mapper;

    /**
     * 查询图片信息表
     */
    @Override
    public List<LookingBooksVo> selectLookingBooksList(LookingBooksDto dto) {
        return mapper.selectListLookingBooks(dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<LookingBooksVo> selectPageLookingBooks(LookingBooksDto dto) {
        Page<LookingBooksVo> page = new Page<>();
    page.setCurrent(dto.getPageNum());
    page.setSize(dto.getPageSize());
        return mapper.selectPageLookingBooks(page, dto);
    }

    /**
     * 分页查询图片信息表
     */
    @Override
    public Page<LookingBooksVo> getPageLookingBooks(LookingBooksDto dto) {
        Page<LookingBooksVo> page = new Page<>();
    page.setCurrent(dto.getPageNum());
    page.setSize(dto.getPageSize());
        QueryWrapper<LookingBooksVo> qw = new QueryWrapper<>();
        return mapper.getPageLookingBooks(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public LookingBooks add(LookingBooksDto dto) {
        LookingBooks value = new LookingBooks();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        mapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(LookingBooksDto dto) {
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
    public LookingBooksVo getInfo(Long id) {
        return mapper.getInfo(id);
    }

}
