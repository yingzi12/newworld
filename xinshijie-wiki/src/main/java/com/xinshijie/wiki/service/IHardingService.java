package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.Harding;
import com.xinshijie.wiki.dto.HardingDto;
import com.xinshijie.wiki.vo.HardingVo;

import java.util.List;

/**
 * <p>
 * 收藏 服务类
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
public interface IHardingService extends IService<Harding> {

    /**
     * 查询收藏
     */
    List<HardingVo> selectHardingList(HardingDto dto);

    /**
     * 分页查询。普通方法
     * 查询收藏
     */
    Page<HardingVo> selectPageHarding(HardingDto dto);

    /**
     * 分页查询收藏
     */
    Page<HardingVo> getPageHarding(HardingDto dto);

    /**
     * 新增数据
     */
    Harding add(HardingDto dto);

    /**
     * 根据id修改数据
     */
    Integer edit(HardingDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    HardingVo getInfo(Long id);

    Integer harding(Integer wid, Long sid);

    Integer hardingClose(Long sid);

    HardingVo getInfoBySidUserid(Long sid, Long userid);

}
