package com.xinshijie.wiki.service.impl;


import cn.hutool.core.thread.ThreadException;
import com.xinshijie.common.enums.ApplySatusEnums;
import com.xinshijie.common.enums.ManageTypeEnums;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.domain.ApplyAuthor;
import com.xinshijie.wiki.dto.ApplyAuthorDto;
import com.xinshijie.wiki.dto.ApplyAuthorFindDto;
import com.xinshijie.wiki.dto.AuthorDto;
import com.xinshijie.wiki.mapper.ApplyAuthorMapper;
import com.xinshijie.wiki.service.IApplyAuthorService;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.service.IRedidentService;
import com.xinshijie.wiki.vo.ApplyAuthorVo;
import com.xinshijie.wiki.vo.AuthorVo;
import com.xinshijie.wiki.vo.RedidentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 申请作者 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-02-06
 */
@Slf4j
@Service
public class ApplyAuthorServiceImpl implements IApplyAuthorService {
    @Autowired
    private ApplyAuthorMapper applyAuthorMapper;
    @Autowired
    private IRedidentService redidentService;
    @Autowired
    private IAuthorService authorService;
    @Override
    public List<ApplyAuthorVo> list(ApplyAuthorFindDto findDto) {
        return applyAuthorMapper.selectByWidSid(findDto);
    }

    @Override
    public List<ApplyAuthorVo> getApplyAuthorList(Integer wid, Long sid) {
        return applyAuthorMapper.selectWidSidUserId(wid, sid,null, ApplySatusEnums.WAIT.getCode());
    }

    @Override
    public List<ApplyAuthorVo> listUser(Long userId) {
        return applyAuthorMapper.selectByUserid(userId);
    }

    @Override
    public ApplyAuthorVo add(ApplyAuthorDto dto) {
        RedidentVo redidentVo= redidentService.selectWidUserId(dto.getWid(),dto.getApplyId());
        if(redidentVo == null || redidentVo.getRanks() == null || redidentVo.getRanks() <3){
            throw new ServiceException(ResultCodeEnum.APPLY_AUTHOR_ERROR);
        }
        List<ApplyAuthorVo> list= applyAuthorMapper.selectWidSidUserId(dto.getWid(),dto.getSid(),dto.getApplyId(), ApplySatusEnums.WAIT.getCode());
        if(list !=null && list.size() >0){
            throw new ServiceException(ResultCodeEnum.APPLY_AUTHOR_REPEAT_ERROR);
        }
        ApplyAuthor applyAuthor = new ApplyAuthor();
        BeanUtils.copyProperties(dto, applyAuthor);
        applyAuthor.setStatus(ApplySatusEnums.WAIT.getCode());
        applyAuthorMapper.insert(applyAuthor);
        ApplyAuthorVo vo = new ApplyAuthorVo();
        BeanUtils.copyProperties(vo, applyAuthor);
        return vo;
    }

    @Override
    public Integer audit(ApplyAuthorDto dto) {
        AuthorVo authorVo = authorService.selectBySidUserId(dto.getSid(), dto.getAuditId());
        //只有创造者才能审核
        if(authorVo == null || !ManageTypeEnums.AUTHOR.getCode().equals(authorVo.getTypes())){
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        ApplyAuthor vo= applyAuthorMapper.selectById(dto.getId());
        if(vo !=null){
            throw new ServiceException(ResultCodeEnum.APPLY_AUTHOR_REPEAT_ERROR);
        }
        if(vo.getWid() != dto.getWid() || vo.getSid() != vo.getSid()){
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        applyAuthorMapper.audit(dto);
        if(ApplySatusEnums.AUDIT.getCode() == dto.getStatus()){
            AuthorDto authorDto=new AuthorDto();
            authorDto.setCreateId(dto.getAuditId());
            authorDto.setCreateTime(LocalDateTime.now());
            authorDto.setCreateName(dto.getAuditName());
            authorDto.setUserId(vo.getApplyId());
            authorDto.setUserName(vo.getApplyName());
            authorDto.setRanks(0);
            authorDto.setTypes(ManageTypeEnums.WRITER.getCode());
            authorDto.setSid(vo.getSid());
            authorDto.setWid(vo.getWid());
//            authorDto.setSname(vo.getSname());

            authorService.add(authorDto);
        }
        return 1;
    }

    @Override
    public Integer close(Long id, Long userId) {
        return applyAuthorMapper.close(id);
    }
}
