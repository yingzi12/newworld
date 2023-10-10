package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.LookingBooks;
import com.xinshijie.wiki.dto.LookingBooksDto;
import com.xinshijie.wiki.vo.LookingBooksVo;
import java.util.List;

/**
 * <p>
 * 寻找书 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-10-04
 */
public interface ILookingBooksService extends IService<LookingBooks> {


    /**
  * 查询信息表
  */
    List<LookingBooksVo> selectLookingBooksList(LookingBooksDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<LookingBooksVo> selectPageLookingBooks(LookingBooksDto dto);

    /**
     * 分页查询信息表
     */
    Page<LookingBooksVo> getPageLookingBooks(LookingBooksDto dto);

    /**
     * 新增数据
     */
    LookingBooks add(LookingBooksDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(LookingBooksDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    LookingBooksVo getInfo(Long id);
}
