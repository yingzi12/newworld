package com.xinshijie.wiki.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xinshijie.wiki.domain.WorldUserLog;
import com.xinshijie.wiki.dto.WorldUserLogDto;
import com.xinshijie.wiki.vo.WorldUserLogVo;
import java.util.List;

/**
 * <p>
 * 世界用户日志 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-10-04
 */
public interface IWorldUserLogService extends IService<WorldUserLog> {


    /**
  * 查询信息表
  */
    List<WorldUserLogVo> selectWorldUserLogList(WorldUserLogDto dto);

    /**
     * 分页查询。普通方法
     * 查询图片信息表
     */
    Page<WorldUserLogVo> selectPageWorldUserLog(WorldUserLogDto dto);

    /**
     * 分页查询信息表
     */
    Page<WorldUserLogVo> getPageWorldUserLog(WorldUserLogDto dto);

    /**
     * 新增数据
     */
    WorldUserLog add(WorldUserLogDto id);

    /**
     * 根据id修改数据
     */
    Integer edit(WorldUserLogDto dto);

    /**
     * 删除数据
     */
    Integer delById(Long id);

    /**
     * 根据id数据
     */
    WorldUserLogVo getInfo(Long id);
}
