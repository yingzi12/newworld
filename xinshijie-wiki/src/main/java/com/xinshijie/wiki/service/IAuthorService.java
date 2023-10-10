package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Author;
import com.xinshijie.wiki.dto.AuthorDto;
import com.xinshijie.wiki.dto.AuthorFindDto;
import com.xinshijie.wiki.vo.AuthorVo;

import java.util.List;

/**
 * <p>
 * 作家 服务类
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
public interface IAuthorService extends IService<Author> {

    /**
     * 查询作家
     */
    List<AuthorVo> selectAuthorList(AuthorFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询作家
     */
    Page<AuthorVo> selectPageAuthor(AuthorFindDto dto);

    /**
     * 分页查询作家
     */
    Page<AuthorVo> getPageAuthor(AuthorFindDto dto);

    List<AuthorVo> selectAuthorBysid(Long sid);

    int insertAuthor(AuthorDto dto);

    /**
     * 新增数据
     */
    Author add(AuthorDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(AuthorDto dto);

    /**
     * 删除数据
     */
    Integer delById(Integer id);

    /**
     * 根据id数据
     */
    AuthorVo getInfo(Integer id, Long sid);

    int deleteAuthorById(Integer id, Long sid);

    AuthorVo isCheck(Long sid);

    AuthorVo selectBySidUserId(Long sid, Long uid);

    void updateCount(AuthorDto dto);

    Long countBySidUserId(Long sid, Long userId);

    List<AuthorVo> selectBySid(Long sid);
}
