package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.wiki.domain.Author;
import com.xinshijie.wiki.dto.AuthorDto;
import com.xinshijie.wiki.dto.AuthorFindDto;
import com.xinshijie.wiki.vo.AuthorVo;
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
public interface AuthorMapper extends BaseMapper<Author> {

    /**
     * 查询管理员
     */
    List<AuthorVo> selectAuthorList(AuthorFindDto dto);

    /**
     * 普通方法
     * 分页查询管理员
     */
    Page<AuthorVo> selectPageAuthor(Page<AuthorVo> page, @Param("dto") AuthorFindDto dto);

    /**
     * 分页查询管理员
     * 基于 MyBatis-Plus 的写法，xml文件中的 ${ew.customSqlSegment} 会根据 Wrapper wrapper的传参自动生成wherer 条件。不推荐复杂where或者是多表联合查询
     */
    Page<AuthorVo> getPageAuthor(Page<AuthorVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);

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
    AuthorVo getInfoBySid(@Param("id") Integer id, @Param("sid") Long sid);

    AuthorVo getInfo(@Param("id") Integer id);
    /**
     * 删除管理员数
     *
     * @param id 管理员数主键
     * @return 结果
     */
    int deleteByIdSid(@Param("id") Integer id, @Param("sid") Long sid);

    AuthorVo selectBySidUserId(@Param("sid") Long sid, @Param("userId") Long userId);

    Long countBySidUserId(@Param("sid") Long sid, @Param("userId") Long userId);

    Integer updateCount(AuthorDto dto);

    Integer updateRank( @Param("id")Integer id, @Param("upgrade")Integer upgrade);

    List<AuthorVo> selectBySid(@Param("sid") Long sid);

}
