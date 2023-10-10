package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Manage;
import com.xinshijie.wiki.dto.ManageDto;
import com.xinshijie.wiki.dto.ManageFindDto;
import com.xinshijie.wiki.vo.ManageVo;

import java.util.List;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IManageService extends IService<Manage> {

    /**
     * 查询管理员
     */
    List<ManageVo> selectManageList(ManageFindDto dto);

    /**
     * 分页查询。普通方法
     * 查询管理员
     */
    Page<ManageVo> selectPageManage(ManageFindDto dto);

    /**
     * 分页查询管理员
     */
    Page<ManageVo> getPageManage(ManageFindDto dto);

    List<ManageVo> selectManageByWid(Integer wid);

    Integer insertManage(ManageDto dto);

    /**
     * 新增数据
     */
    Manage add(ManageDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(ManageDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    ManageVo getInfo(Long id);

    int deleteManageById(Long id, Integer wid);

    ManageVo isCheck(Integer wid);

    ManageVo selectByWidUserId(Integer wid, Long uid);

    void updateCount(ManageDto dto);

    Long countByWidUserId(Integer wid, Long userId);


}
