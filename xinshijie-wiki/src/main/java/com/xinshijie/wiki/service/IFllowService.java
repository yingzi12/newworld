package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Fllow;
import com.xinshijie.wiki.dto.FllowDto;
import com.xinshijie.wiki.vo.FllowVo;

import java.util.List;

/**
 * <p>
 * 关注 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IFllowService extends IService<Fllow> {

    /**
     * 查询关注
     */
    List<FllowVo> selectFllowList(FllowDto dto);

    /**
     * 分页查询。普通方法
     * 查询关注
     */
    Page<FllowVo> selectPageFllow(FllowDto dto);

    /**
     * 分页查询关注
     */
    Page<FllowVo> getPageFllow(FllowDto dto);

    /**
     * 新增数据
     */
    Fllow add(FllowDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(FllowDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    FllowVo getInfo(Long id);

    FllowVo getInfoByWidUserid(Integer wid, Long userid);

    Integer fllow(Integer wid);

    Integer fllowClose(Integer wid);

}
