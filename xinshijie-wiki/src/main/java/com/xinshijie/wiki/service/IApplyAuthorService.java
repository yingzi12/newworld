package com.xinshijie.wiki.service;

import com.xinshijie.wiki.dto.ApplyAuthorDto;
import com.xinshijie.wiki.dto.ApplyAuthorFindDto;
import com.xinshijie.wiki.vo.ApplyAuthorVo;

import java.util.List;

/**
 * <p>
 * 申请作者 服务类
 * </p>
 *
 * @author 作者
 * @since 2023-02-06
 */
public interface IApplyAuthorService {

    List<ApplyAuthorVo> list(ApplyAuthorFindDto findDto);

    List<ApplyAuthorVo> getApplyAuthorList(Integer wid, Long sid);
    List<ApplyAuthorVo> listUser(Long userId);

    ApplyAuthorVo add(ApplyAuthorDto dto);

    Integer audit(ApplyAuthorDto dto);

    Integer close(Long id, Long userId);

}
