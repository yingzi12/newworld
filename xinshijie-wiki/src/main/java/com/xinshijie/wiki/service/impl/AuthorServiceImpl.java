package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ManageTypeEnums;
import com.xinshijie.common.enums.RankEnums;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.BeanUtils;
import com.xinshijie.common.utils.SecurityUtils;
import com.xinshijie.wiki.domain.Author;
import com.xinshijie.wiki.dto.AuthorDto;
import com.xinshijie.wiki.dto.AuthorFindDto;
import com.xinshijie.wiki.mapper.AuthorMapper;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.service.IStoryService;
import com.xinshijie.wiki.vo.AuthorVo;
import com.xinshijie.wiki.vo.StoryVo;
import com.xinshijie.wiki.vo.WorldVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 作家 服务实现类
 * </p>
 *
 * @author zx
 * @since 2022-09-22
 */
@Slf4j
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements IAuthorService {

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private IStoryService storyService;

    /**
     * 查询作家
     */
    @Override
    public List<AuthorVo> selectAuthorList(AuthorFindDto dto) {
        return authorMapper.selectAuthorList(dto);
    }

    /**
     * 分页查询作家
     */
    @Override
    public Page<AuthorVo> selectPageAuthor(AuthorFindDto dto) {
        Page<AuthorVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        return authorMapper.selectPageAuthor(page, dto);
    }

    /**
     * 分页查询作家
     */
    @Override
    public Page<AuthorVo> getPageAuthor(AuthorFindDto dto) {
        Page<AuthorVo> page = new Page<>();
        page.setCurrent(dto.getPage());
        page.setSize(dto.getSize());
        QueryWrapper<AuthorVo> qw = new QueryWrapper<>();
        return authorMapper.getPageAuthor(page, qw);
    }

    @Override
    public List<AuthorVo> selectAuthorBysid(Long sid) {
        AuthorFindDto vo = new AuthorFindDto();
        vo.setSid(sid);
        return authorMapper.selectAuthorList(vo);
    }

    /**
     * 新增数据
     */
    @Override
    public Author add(AuthorDto dto) {
        Author value = new Author();
        org.springframework.beans.BeanUtils.copyProperties(dto, value);
        value.setRanks(0);
        value.setCredit(0);
        value.setIncomde(0);
        value.setMonthlyIncomde(0);
        value.setCountAuditYes(0);
        value.setCountAuditNo(0);
        value.setCountComment(0);
        value.setCountDiscuss(0);
        value.setCountEdit(0);
        value.setCountAudit(0);
        value.setCountChapter(0);
        value.setCountDel(0);
        authorMapper.insert(value);
        return value;
    }

    /**
     * 新增管理员数
     *
     * @param dto 管理员数
     * @return 结果
     */
    @Override
    public int insertAuthor(AuthorDto dto) {
        // 获取当前用户名
        String username = SecurityUtils.getUsername();
        // 获取当前用户ID
        Long userid = SecurityUtils.getUserId();
        Long count = authorMapper.countBySidUserId(dto.getSid(), dto.getUserId());
        if (count > 0) {
            throw new ServiceException(ResultCodeEnum.USERNAME_DOES_NOT_EXIST);
        }
        AuthorVo AuthorVo = authorMapper.selectBySidUserId(dto.getSid(), userid);
        if (AuthorVo.getTypes() != ManageTypeEnums.AUTHOR.getCode()) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }

        StoryVo story = storyService.getInfo(dto.getSid());
        Author author = new Author();
        BeanUtils.copyProperties(dto, author);
        author.setSid(story.getId());
        author.setSname(story.getName());
        author.setWid(story.getWid());
        author.setWname(story.getWname());
        author.setCreateName(username);
        author.setTypes(ManageTypeEnums.WRITER.getCode());
        author.setCreateId(userid);
        author.setUpdateId(userid);
        author.setUpdateName(username);
        author.setCreateTime(LocalDateTime.now());
        author.setUpdateTime(LocalDateTime.now());
        return authorMapper.insert(author);
    }

    /**
     * 根据id修改数据
     */
    @Override
    public Integer edit(AuthorDto dto) {
        return authorMapper.edit(dto);
    }

    /**
     * 删除数据
     */
    @Override
    public Integer delById(Integer id) {
        return authorMapper.delById(id);
    }

    /**
     * 根据id数据
     */
    @Override
    public AuthorVo getInfo(Integer id, Long sid) {
        return authorMapper.getInfoBySid(id, sid);
    }

    /**
     * 删除管理员数信息
     *
     * @param id 管理员数主键
     * @return 结果
     */
    @Override
    public int deleteAuthorById(Integer id, Long sid) {
        Long userid = SecurityUtils.getUserId();
        AuthorVo Author = authorMapper.selectBySidUserId(sid, userid);
        if (Author == null) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        if (Author.getTypes() != ManageTypeEnums.AUTHOR.getCode()) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        return authorMapper.deleteByIdSid(id, sid);
    }

    @Override
    public AuthorVo isCheck(Long sid) {
        Long userid = SecurityUtils.getUserId();
        if (sid == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        AuthorVo author = authorMapper.selectBySidUserId(sid, userid);
        if (author == null) {
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        return author;
    }

    @Override
    public AuthorVo selectBySidUserId(Long sid, Long uid) {
        return authorMapper.selectBySidUserId(sid, uid);
    }

    @Override
    public void updateCount(AuthorDto dto) {
        //更新世界
        authorMapper.updateCount(dto);
        AuthorVo vo=authorMapper.getInfo(dto.getId());
        Integer upgrade= RankEnums.getRankEnums(vo.getRanks()+1).getCredit();
        if(vo.getCredit() >= upgrade ){
            authorMapper.updateRank(vo.getId(),upgrade);
        }
    }

    @Override
    public Long countBySidUserId(Long sid, Long userId) {
        return authorMapper.countBySidUserId(sid, userId);
    }

    @Override
    public List<AuthorVo> selectBySid(Long sid) {
        return authorMapper.selectBySid(sid);
    }
}
