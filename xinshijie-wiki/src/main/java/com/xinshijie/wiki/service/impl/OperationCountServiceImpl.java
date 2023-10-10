package com.xinshijie.wiki.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.xinshijie.common.enums.UserOptionEnums;
import com.xinshijie.wiki.dto.*;
import com.xinshijie.wiki.service.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OperationCountServiceImpl implements IOperationCountService {
    @Autowired
    private IWorldService worldService;
    @Autowired
    private IManageService manageService;
    @Autowired
    private IRedidentService redidentService;
    @Autowired
    private IUserService userService;

    private IStoryService storyService;

    private IAuthorService authorService;

    @Override
    public void updateCount(Integer wid, Long userId, UserOptionEnums userOptionEnums) {
        log.info("统计 wiod:{},userId:{},操作:{}", wid, userId, userOptionEnums.toString());
        //更新世界
        updateWorldCount(wid, userOptionEnums);
        updateManageCount(wid, userId, userOptionEnums);
        updateUserCount(userId, userOptionEnums);
        updateRedidentCount(wid, userId, userOptionEnums);
//        if(sid!=null){
//            updateAuthorCount(sid,userId,userOptionEnums);
//            updateStoryCount(sid,userOptionEnums);
//        }
    }

    public void updateWorldCount(Integer wid, UserOptionEnums userOptionEnums) {
        log.info("统计 wid:{},操作:{}", wid, JSONObject.toJSONString(userOptionEnums));

        WorldDto dto = new WorldDto();
        dto.setId(wid);
        dto.setVitality(userOptionEnums.getVitality());
        switch (userOptionEnums) {
//            ADDELEMENT(0, "添加元素",8,10,10,0,0),
            case ADD_ELEMENT:
                dto.setCountElement(1);
                break;
//                    ADDWORLD(1, "添加世界",10,12,12,0,0),
//                    ADDCOMMENT(2, "添加评论",3,1,1,0,0),
            case ADD_COMMENT:
                dto.setCountComment(1);
                break;
            case DEL_COMMENT:
                dto.setCountComment(-1);
                break;
//                    ADDDISSCUS(3, "添加讨论",5,1,1,0,0),
            case ADD_DISCUSS:
                dto.setCountDiscuss(1);
                break;
            case DEL_DISCUSS:
                dto.setCountDiscuss(-1);
                break;
//                    FLLOW(4, "关注",1,0,1,0,0),
            case ADD_FOLLOW:
                dto.setCountFllow(1);
                break;
            case DEL_FOLLOW:
                dto.setCountFllow(-1);
                break;
//                    REPLYCOOMENT(5, "回复评论",2,1,1,0,0),
//                    REPLYDISSCUS(6, "回复讨论",2,1,1,0,0),
//                    EDITELEMNT(7, "编辑元素",3,1,2,0,0),
            case EDIT_ELEMENT:
                dto.setCountEdit(1);
                break;
            //                    AUDITELEMENT(8, "审核元素",3,3,1,0,0),
//                    AUDITELEMENTYES(9, "元素审核通过",3,5,3,0,0),
//                    SEEELEMT(10, "查看元素",1,0,1,0,0),
//                    ADDREDIDENT(11, "新居民",0,1,0,0,0),
//                    SIGIN(12, "签到",1,0,0,0,0),
//                    WORLDSIGIN(13, "世界签到",1,1,1,0,0),
//                    DELWORLD(14, "删除元素",-8,-10,-10,0,0),
//                    DELELEMNT(15, "删除元素",-8,-10,-10,0,0),
//                    DELCOMMNENT(16, "删除评论",-8,-10,-10,0,0),
//                    DELDISCUSS(17, "删除讨论",-8,-10,-10,0,0),
//                    DELFLLOW(18, "取消关注",-8,-10,-10,0,0),
            case ADD_STORY:
                dto.setCountStory(1);
                break;
            case DEL_STORY:
                dto.setCountStory(-1);
                break;
        }
        worldService.updateCount(dto);
    }

    public void updateManageCount(Integer wid, Long userId, UserOptionEnums userOptionEnums) {
        log.info("统计 wiod:{},userId:{},操作:{}", wid, userId, JSONObject.toJSONString(userOptionEnums));
        ManageDto dto = new ManageDto();
        dto.setWid(wid);
        dto.setUserId(userId);
        dto.setCredit(userOptionEnums.getCredit());
        switch (userOptionEnums) {
//            ADDELEMENT(0, "添加元素",8,10,10,0,0),
            case ADD_ELEMENT:
                dto.setCountElement(1);
                break;
//                    ADDCOMMENT(2, "添加评论",3,1,1,0,0),
            case ADD_COMMENT:
                dto.setCountComment(1);
                break;
//                    ADDDISSCUS(3, "添加讨论",5,1,1,0,0),
            case ADD_DISCUSS:
                dto.setCountDiscuss(1);
                break;
//                    FLLOW(4, "关注",1,0,1,0,0),
//                    REPLYCOOMENT(5, "回复评论",2,1,1,0,0),
//                    REPLYDISSCUS(6, "回复讨论",2,1,1,0,0),
//                    EDITELEMNT(7, "编辑元素",3,1,2,0,0),
            case EDIT_ELEMENT:
                dto.setCountEdit(1);
                break;
//                    AUDITELEMENT(8, "审核元素",3,3,1,0,0),
            case AUDIT_ELEMENT:
                dto.setCountAudit(1);
                break;
            case AUDIT_ELEMENT_YES:
                dto.setCountAuditYes(1);
                break;
//                    AUDITELEMENTYES(9, "元素审核通过",3,5,3,0,0),
//                    SEEELEMT(10, "查看元素",1,0,1,0,0),
//                    ADDREDIDENT(11, "新居民",0,1,0,0,0),
//                    SIGIN(12, "签到",1,0,0,0,0),
//                    WORLDSIGIN(13, "世界签到",1,1,1,0,0),
//                    DELWORLD(14, "删除元素",-8,-10,-10,0,0),
//                    DELELEMNT(15, "删除元素",-8,-10,-10,0,0),
//                    DELCOMMNENT(16, "删除评论",-8,-10,-10,0,0),
//                    DELDISCUSS(17, "删除讨论",-8,-10,-10,0,0),
//                    DELFLLOW(18, "取消关注",-8,-10,-10,0,0),
        }
        manageService.updateCount(dto);
    }

    public void updateRedidentCount(Integer wid, Long userId, UserOptionEnums userOptionEnums) {
        log.info("统计 wid:{},userId:{},操作:{}", wid, userId, userOptionEnums.toString());

        RedidentDto dto = new RedidentDto();
        dto.setWid(wid);
        dto.setCreateId(userId);
        dto.setCredit(userOptionEnums.getCredit());
        switch (userOptionEnums) {
//            ADDELEMENT(0, "添加元素",8,10,10,0,0),
            case ADD_ELEMENT:
                dto.setCountElement(1);
                break;
//                    ADDCOMMENT(2, "添加评论",3,1,1,0,0),
            case ADD_COMMENT:
                dto.setCountComment(1);
                break;
//                    ADDDISSCUS(3, "添加讨论",5,1,1,0,0),
            case ADD_DISCUSS:
                dto.setCountDiscuss(1);
                break;
//                    FLLOW(4, "关注",1,0,1,0,0),
//                    REPLYCOOMENT(5, "回复评论",2,1,1,0,0),
//                    REPLYDISSCUS(6, "回复讨论",2,1,1,0,0),
//                    EDITELEMNT(7, "编辑元素",3,1,2,0,0),
            case EDIT_ELEMENT:
                dto.setCountEdit(1);
                break;
//                    AUDITELEMENT(8, "审核元素",3,3,1,0,0),
//                    AUDITELEMENTYES(9, "元素审核通过",3,5,3,0,0),
//                    SEEELEMT(10, "查看元素",1,0,1,0,0),
//                    ADDREDIDENT(11, "新居民",0,1,0,0,0),
//                    SIGIN(12, "签到",1,0,0,0,0),
//                    WORLDSIGIN(13, "世界签到",1,1,1,0,0),
//                    DELWORLD(14, "删除元素",-8,-10,-10,0,0),
//                    DELELEMNT(15, "删除元素",-8,-10,-10,0,0),
//                    DELCOMMNENT(16, "删除评论",-8,-10,-10,0,0),
//                    DELDISCUSS(17, "删除讨论",-8,-10,-10,0,0),
//                    DELFLLOW(18, "取消关注",-8,-10,-10,0,0),
        }
        redidentService.updateCount(dto);
    }

    public void updateUserCount(Long userId, UserOptionEnums userOptionEnums) {
        log.info("统计 userId:{},操作:{}", userId, userOptionEnums.toString());

        UserDto dto = new UserDto();
        dto.setUserId(userId);
        dto.setExp(userOptionEnums.getExp());
        switch (userOptionEnums) {
//            ADDELEMENT(0, "添加元素",8,10,10,0,0),
            case ADD_ELEMENT:
                dto.setCountElement(1);
                break;
            case ADD_WORLD:
                dto.setCountWorld(1);
                break;
//                    ADDCOMMENT(2, "添加评论",3,1,1,0,0),
            case ADD_COMMENT:
                dto.setCountComment(1);
                break;
//                    ADDDISSCUS(3, "添加讨论",5,1,1,0,0),
            case ADD_DISCUSS:
                dto.setCountDiscuss(1);
                break;
//                    FLLOW(4, "关注",1,0,1,0,0),
            case ADD_FOLLOW:
                dto.setCountFllow(1);
                break;
//                    REPLYCOOMENT(5, "回复评论",2,1,1,0,0),
//                    REPLYDISSCUS(6, "回复讨论",2,1,1,0,0),
//                    EDITELEMNT(7, "编辑元素",3,1,2,0,0),
            case EDIT_ELEMENT:
                dto.setCountEdit(1);
                break;
//                    AUDITELEMENT(8, "审核元素",3,3,1,0,0),
//                    AUDITELEMENTYES(9, "元素审核通过",3,5,3,0,0),
//                    SEEELEMT(10, "查看元素",1,0,1,0,0),
//                    ADDREDIDENT(11, "新居民",0,1,0,0,0),
//                    SIGIN(12, "签到",1,0,0,0,0),
//                    WORLDSIGIN(13, "世界签到",1,1,1,0,0),
//                    DELWORLD(14, "删除元素",-8,-10,-10,0,0),
//                    DELELEMNT(15, "删除元素",-8,-10,-10,0,0),
//                    DELCOMMNENT(16, "删除评论",-8,-10,-10,0,0),
//                    DELDISCUSS(17, "删除讨论",-8,-10,-10,0,0),
//                    DELFLLOW(18, "取消关注",-8,-10,-10,0,0),
            case DEL_FOLLOW:
                dto.setCountFllow(-1);
                break;
            case ADD_HARDING:
                dto.setCountHarding(1);
                break;
            case DEL_HARDING:
                dto.setCountHarding(-1);
                break;
        }
        userService.updateCount(dto);
    }

    public void updateStoryCount(Long sid, UserOptionEnums userOptionEnums) {
        log.info("统计 sid:{},userId:{},操作:{}", sid, userOptionEnums.toString());
        StoryDto dto = new StoryDto();
        dto.setId(sid);
        dto.setDiffuse(userOptionEnums.getDiffuse());
        switch (userOptionEnums) {
//            ADDELEMENT(0, "添加元素",8,10,10,0,0),
//                    ADDCOMMENT(2, "添加评论",3,1,1,0,0),
            case ADD_COMMENT:
                dto.setCountComment(1);
                break;
//                    ADDDISSCUS(3, "添加讨论",5,1,1,0,0),
            case DEL_COMMENT:
                dto.setCountComment(-1);
                break;
            case ADD_HARDING:
                dto.setCountHarding(1);
                break;
            case DEL_HARDING:
                dto.setCountHarding(-1);
                break;
            case ADD_CHAPTER:
                dto.setCountChapter(1);
            case DEL_CHAPTER:
                dto.setCountChapter(-1);
                break;
            case EDIT_CHAPTER:
                dto.setCountEdit(1);
                break;
            case ADD_AUTHOR:
                dto.setCountAuthor(1);
                break;
            case DEL_AUTHOR:
                dto.setCountAuthor(-1);
                break;
        }
        storyService.updateCount(dto);
    }


    public void updateAuthorCount(Long sid, Long userId, UserOptionEnums userOptionEnums) {
        log.info("统计 sid:{},userId:{},操作:{}", sid, userId, userOptionEnums.toString());
        AuthorDto dto = new AuthorDto();
        dto.setSid(sid);
        dto.setUserId(userId);
        dto.setCredit(userOptionEnums.getCredit());
        switch (userOptionEnums.getCode()) {
//            ADDELEMENT(0, "添加元素",8,10,10,0,0),
//                    ADDCOMMENT(2, "添加评论",3,1,1,0,0),
//                    ADDDISSCUS(3, "添加讨论",5,1,1,0,0),
//                    FLLOW(4, "关注",1,0,1,0,0),
//                    REPLYCOOMENT(5, "回复评论",2,1,1,0,0),
//                    REPLYDISSCUS(6, "回复讨论",2,1,1,0,0),
//                    EDITELEMNT(7, "编辑元素",3,1,2,0,0),
//                    AUDITELEMENT(8, "审核元素",3,3,1,0,0),
//                    AUDITELEMENTYES(9, "元素审核通过",3,5,3,0,0),
//                    SEEELEMT(10, "查看元素",1,0,1,0,0),
//                    ADDREDIDENT(11, "新居民",0,1,0,0,0),
//                    SIGIN(12, "签到",1,0,0,0,0),
//                    WORLDSIGIN(13, "世界签到",1,1,1,0,0),
//                    DELWORLD(14, "删除元素",-8,-10,-10,0,0),
//                    DELELEMNT(15, "删除元素",-8,-10,-10,0,0),
//                    DELCOMMNENT(16, "删除评论",-8,-10,-10,0,0),
//                    DELDISCUSS(17, "删除讨论",-8,-10,-10,0,0),
//                    DELFLLOW(18, "取消关注",-8,-10,-10,0,0),
        }
        authorService.updateCount(dto);
    }
}
