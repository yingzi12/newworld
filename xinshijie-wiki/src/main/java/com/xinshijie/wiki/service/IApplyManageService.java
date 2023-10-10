package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.ApplyManage;
import com.xinshijie.wiki.dto.ApplyManageDto;
import com.xinshijie.wiki.dto.ApplyManageFindDto;
import com.xinshijie.wiki.vo.ApplyManageVo;

import java.util.List;

/**
 * <p>
 * 申请管理员 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-02-06
 */
public interface IApplyManageService extends IService<ApplyManage> {

    /**
     * 根据wid查询申请列表
     */
    List<ApplyManageVo> list(ApplyManageFindDto findDto);

    List<ApplyManageVo> getWorldManageList(Integer wid);
    /**
     * 查询本人的申请列表
     */
    List<ApplyManageVo> listUser(Long userId);


    /**
     * 发起申请
     */
    ApplyManageVo add(ApplyManageDto dto);

    /**
     * 审核申请
     */
    Integer audit(ApplyManageDto dto);

    /**
     * 关闭申请
     */
    Integer close(Long id, Long userId);

}
