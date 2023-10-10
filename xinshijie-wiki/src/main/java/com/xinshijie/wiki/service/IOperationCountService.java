package com.xinshijie.wiki.service;

import com.xinshijie.common.enums.UserOptionEnums;

public interface IOperationCountService {
    void updateCount(Integer wid,Long userId, UserOptionEnums userOptionEnums);

    void updateWorldCount(Integer wid, UserOptionEnums userOptionEnums);

    void updateManageCount(Integer wid, Long userId, UserOptionEnums userOptionEnums);

    void updateRedidentCount(Integer wid, Long userId, UserOptionEnums userOptionEnums);

    void updateStoryCount(Long sid, UserOptionEnums userOptionEnums);

    void updateAuthorCount(Long sid, Long userId, UserOptionEnums userOptionEnums) ;

    void updateUserCount(Long userId, UserOptionEnums userOptionEnums);

}
