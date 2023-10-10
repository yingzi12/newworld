package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Bookshelf;
import com.xinshijie.wiki.dto.BookshelfDto;
import com.xinshijie.wiki.vo.BookshelfVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 书架 Mapper 接口
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
public interface BookshelfMapper extends BaseMapper<Bookshelf> {

    /**
     * 查询书架
     */
    List<BookshelfVo> selectBookshelfList(BookshelfDto dto);

    /**
     * 普通方法
     * 分页查询书架
     */
    Page<BookshelfVo> selectPageBookshelf(Page<BookshelfVo> page, @Param("dto") BookshelfDto dto);

    /**
     * 分页查询书架
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<BookshelfVo> getPageBookshelf(Page<BookshelfVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

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
