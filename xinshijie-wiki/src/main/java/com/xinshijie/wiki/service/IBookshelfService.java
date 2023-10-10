package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Bookshelf;
import com.xinshijie.wiki.dto.BookshelfDto;
import com.xinshijie.wiki.vo.BookshelfVo;

import java.util.List;

/**
 * <p>
 * 书架 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-21
 */
public interface IBookshelfService extends IService<Bookshelf> {

    /**
     * 查询书架
     */
    List<BookshelfVo> selectBookshelfList(BookshelfDto dto);

    /**
     * 分页查询。普通方法
     * 查询书架
     */
    Page<BookshelfVo> selectPageBookshelf(BookshelfDto dto);

    /**
     * 分页查询书架
     */
    Page<BookshelfVo> getPageBookshelf(BookshelfDto dto);

    /**
     * 新增数据
     */
    Bookshelf add(BookshelfDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(BookshelfDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    BookshelfVo getInfo(Long id);
}
