package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinshijie.wiki.domain.ApplyManage;
import com.xinshijie.wiki.dto.ApplyManageDto;
import com.xinshijie.wiki.dto.ApplyManageFindDto;
import com.xinshijie.wiki.vo.ApplyManageVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 申请管理员 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-02-06
 */
public interface ApplyManageMapper extends BaseMapper<ApplyManage> {

    List<ApplyManageVo> selectByWid(ApplyManageFindDto findDto);

    List<ApplyManageVo> selectByUserid(Long userId);

    List<ApplyManageVo> selectWidUserId(@Param("wid") Integer wid, @Param("userId") Long userId,@Param("status") Integer status);

    int close(Long id);

    int audit(ApplyManageDto dto);
}
