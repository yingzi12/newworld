package com.xinshijie.wiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinshijie.wiki.domain.ApplyAuthor;
import com.xinshijie.wiki.dto.ApplyAuthorDto;
import com.xinshijie.wiki.dto.ApplyAuthorFindDto;
import com.xinshijie.wiki.vo.ApplyAuthorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 申请作者 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-02-06
 */
public interface ApplyAuthorMapper extends BaseMapper<ApplyAuthor> {

    List<ApplyAuthorVo> selectByWidSid(ApplyAuthorFindDto findDto);

    List<ApplyAuthorVo> selectByUserid(Long userId);

    List<ApplyAuthorVo> selectWidSidUserId( @Param("wid") Integer wid, @Param("sid")  Long sid,  @Param("userId") Long userId,@Param("status") Integer status);
    int close(Long id);

    int audit(ApplyAuthorDto dto);
}
