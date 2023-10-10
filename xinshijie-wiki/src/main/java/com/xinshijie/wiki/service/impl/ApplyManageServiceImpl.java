package com.xinshijie.wiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinshijie.common.enums.ApplySatusEnums;
import com.xinshijie.common.enums.ManageTypeEnums;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.domain.ApplyAuthor;
import com.xinshijie.wiki.domain.ApplyManage;
import com.xinshijie.wiki.dto.ApplyManageDto;
import com.xinshijie.wiki.dto.ApplyManageFindDto;
import com.xinshijie.wiki.dto.AuthorDto;
import com.xinshijie.wiki.dto.ManageDto;
import com.xinshijie.wiki.mapper.ApplyManageMapper;
import com.xinshijie.wiki.service.IApplyManageService;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.service.IRedidentService;
import com.xinshijie.wiki.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 申请管理员 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-02-06
 */
@Slf4j
@Service
public class ApplyManageServiceImpl extends ServiceImpl<ApplyManageMapper, ApplyManage> implements IApplyManageService {

    @Autowired
    private ApplyManageMapper applyManageMapper;
    @Autowired
    private IRedidentService redidentService;
    @Autowired
    private IManageService manageService;
    @Override
    public List<ApplyManageVo> list(ApplyManageFindDto findDto) {
        return applyManageMapper.selectByWid(findDto);
    }

    @Override
    public List<ApplyManageVo> getWorldManageList(Integer wid) {
        List<ApplyManageVo> list= applyManageMapper.selectWidUserId(wid,null, ApplySatusEnums.WAIT.getCode());
        return list;
    }

    @Override
    public List<ApplyManageVo> listUser(Long userId) {
        return applyManageMapper.selectByUserid(userId);
    }

    @Override
    public ApplyManageVo add(ApplyManageDto dto) {

        RedidentVo redidentVo= redidentService.selectWidUserId(dto.getWid(),dto.getApplyId());
        if(redidentVo == null || redidentVo.getRanks() == null || redidentVo.getRanks() <6){
            throw new ServiceException(ResultCodeEnum.APPLY_WORLD_ERROR);
        }
        List<ApplyManageVo> list= applyManageMapper.selectWidUserId(dto.getWid(),dto.getApplyId(), ApplySatusEnums.WAIT.getCode());
        if(list !=null && list.size() >0){
            throw new ServiceException(ResultCodeEnum.APPLY_WORLD_REPEAT_ERROR);
        }
        ApplyManage ApplyManage = new ApplyManage();
        BeanUtils.copyProperties(dto, ApplyManage);
        applyManageMapper.insert(ApplyManage);

        ApplyManageVo vo = new ApplyManageVo();
        BeanUtils.copyProperties(vo, ApplyManage);

        return vo;
    }

    @Override
    public Integer audit(ApplyManageDto dto) {
        ManageVo manageVo = manageService.selectByWidUserId(dto.getWid(), dto.getAuditId());
        //只有创造者才能审核
        if(manageVo == null || !ManageTypeEnums.ADVANCED.getCode().equals(manageVo.getTypes())){
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        ApplyManage vo= applyManageMapper.selectById(dto.getId());
        if(vo !=null){
            throw new ServiceException(ResultCodeEnum.APPLY_AUTHOR_REPEAT_ERROR);
        }
        if(vo.getWid() != dto.getWid() || vo.getApplyId() != dto.getApplyId() ){
            throw new ServiceException(ResultCodeEnum.INSUFFICIENT_PERMISSIONS);
        }
        applyManageMapper.audit(dto);
        if(ApplySatusEnums.AUDIT.getCode() == dto.getStatus()){
            ManageDto manageDto=new ManageDto();
            manageDto.setCreateId(dto.getAuditId());
            manageDto.setCreateTime(LocalDateTime.now());
            manageDto.setCreateName(dto.getAuditName());
            manageDto.setUserId(vo.getApplyId());
            manageDto.setUserName(vo.getApplyName());
            manageDto.setRanks(0);
            manageDto.setTypes(ManageTypeEnums.WRITER.getCode());
            manageDto.setWid(vo.getWid());
//            authorDto.setSname(vo.getSname());

            manageService.add(manageDto);
        }
        return 1;
    }

    @Override
    public Integer close(Long id, Long userId) {
        return applyManageMapper.close(id);
    }
}
