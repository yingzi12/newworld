package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.wiki.domain.Bookshelf;
import com.xinshijie.wiki.dto.BookshelfDto;
import com.xinshijie.wiki.mapper.BookshelfMapper;
import com.xinshijie.wiki.service.IBookshelfService;
import com.xinshijie.wiki.vo.BookshelfVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 书架 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Slf4j
@Service
public class BookshelfServiceImpl extends ServiceImpl<BookshelfMapper, Bookshelf> implements IBookshelfService {

    @Autowired
    private BookshelfMapper bookshelfMapper;

    /**
     * 查询书架
     */
    @Override
    public List<BookshelfVo> selectBookshelfList(BookshelfDto dto) {
        return bookshelfMapper.selectBookshelfList(dto);
    }

    /**
     * 分页查询书架
     */
    @Override
    public Page<BookshelfVo> selectPageBookshelf(BookshelfDto dto) {
        Page<BookshelfVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return bookshelfMapper.selectPageBookshelf(page, dto);
    }

    /**
     * 分页查询书架
     */
    @Override
    public Page<BookshelfVo> getPageBookshelf(BookshelfDto dto) {
        Page<BookshelfVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<BookshelfVo> qw = new QueryWrapper<>();
        return bookshelfMapper.getPageBookshelf(page, qw);
    }

    /**
     * 新增数据
     */
    @Override
    public Bookshelf add(BookshelfDto dto) {
        Bookshelf value = new Bookshelf();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        bookshelfMapper.insert(value);
        return value;
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(BookshelfDto dto) {
        return bookshelfMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Long id) {
        return bookshelfMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public BookshelfVo getInfo(Long id) {
        return bookshelfMapper.getInfo(id);
    }
}
