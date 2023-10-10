package com.xinshijie.web.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinshijie.common.constant.CacheConstants;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.page.TableDataInfo;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.wiki.domain.Comment;
import com.xinshijie.wiki.dto.CommentAddDto;
import com.xinshijie.wiki.dto.CommentDto;
import com.xinshijie.wiki.dto.CommentFindDto;
import com.xinshijie.wiki.dto.CommentReplyAddDto;
import com.xinshijie.wiki.service.IAuthorService;
import com.xinshijie.wiki.service.ICommentService;
import com.xinshijie.wiki.service.IManageService;
import com.xinshijie.wiki.vo.CommentVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-08-27
 */
@Slf4j
@RestController
@Tag(name = "AdminCommentController", description = "后台-评论表")
@RequestMapping("/admin/comment")
public class AdminCommentController extends BaseController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IManageService manageService;

    @Autowired
    private IAuthorService authorService;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询所有
     */
    @GetMapping("/list")
    @Operation(summary = "查询评论表", description = "查询评论表")
    public TableDataInfo<List<CommentVo>> list(CommentFindDto dto) {
        dto.setCreateId(getUserId());
        startPage();
        List<CommentVo> list = commentService.selectCommentList(dto);
        return getDataTable(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/listAdmin")
    @Operation(summary = "查询评论表", description = "查询评论表")
    public TableDataInfo<List<CommentVo>> listAdmin(CommentFindDto dto) {
        if (dto.getSource() == null || (dto.getSource() != 1 && dto.getSource() != 2)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (dto.getSource() == 1) {
            manageService.isCheck(dto.getWid());
        }
        if (dto.getSource() == 2) {
            authorService.isCheck(dto.getSid());
        }
        startPage();
        List<CommentVo> list = commentService.selectCommentList(dto);
        return getDataTable(list);
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询评论表", description = "分页查询评论表")
    public Result<Page<CommentVo>> selectSysRoleList(@RequestBody CommentDto dto) {
        Page<CommentVo> values = commentService.selectPageComment(dto);
        return Result.success(values);
    }

    /**
     * 查询世界评论列表
     */
    @GetMapping("/getWorldComment")
    @Operation(summary = "获取世界评论信息", description = "获取世界评论信息")
    public TableDataInfo<List<CommentVo>> getWorldComment(CommentFindDto findDto) {
        findDto.setSource(1);
        startPage();
        List<CommentVo> list = commentService.selectCommentByWorld(findDto);
        return getDataTable(list);
    }

    @GetMapping("/getStoryComment")
    public TableDataInfo<List<CommentVo>> getStoryComment(CommentFindDto findDto) {
        findDto.setSource(2);
        startPage();
        List<CommentVo> list = commentService.selectCommentByStory(findDto);
        return getDataTable(list);
    }

    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增评论", description = "新增评论")
    public Result<Comment> add(@RequestBody CommentAddDto dto) {
        if (dto.getSource() == null || (dto.getSource() != 1 && dto.getSource() != 2)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (dto.getSource() == 1 && dto.getWid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        if (dto.getSource() == 2 && dto.getSid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        String key = "";
        if (dto.getSid() == null) {
            key = CacheConstants.COMMENT + getUserId() + ":" + dto.getSource() + ":" + dto.getWid();
        } else {
            key = CacheConstants.COMMENT + getUserId() + ":" + dto.getSource() + ":" + dto.getSid();
        }
//        if(redisCache.hasKey(key)){
//            throw new ServiceException("发布过于频繁，请1小时之后在尝试");
//        }else{
//            redisCache.setCacheObject(key,1,10, TimeUnit.MINUTES);
//        }
//        dto.setCircleUrl(getAvatar());
//        dto.setNickname(getNickName());
        return Result.success(commentService.add(dto));
    }

    /**
     * 新增数据
     */
    @PostMapping("/reply")
    @Operation(summary = "回复评论表", description = "回复评论")
    public Result<Comment> reply(@RequestBody CommentReplyAddDto dto) {
        if (dto.getSource() == null || (dto.getSource() != 1 && dto.getSource() != 2)) {
            throw new ServiceException(ResultCodeEnum.OPERATOR_ERROR);
        }
        if (dto.getSource() == 1 && dto.getWid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        if (dto.getSource() == 2 && dto.getSid() == null) {
            throw new ServiceException(ResultCodeEnum.PARAMS_NOT_COMPLETE);
        }
        String key = "";
        if (dto.getSid() == null) {
            key = CacheConstants.COMMENT + getUserId() + ":" + dto.getSource() + ":" + dto.getWid();
        } else {
            key = CacheConstants.COMMENT + getUserId() + ":" + dto.getSource() + ":" + dto.getSid();
        }
//        if(redisCache.hasKey(key)){
//            throw new ServiceException("发布通过频繁，请10分钟之后在尝试");
//        }else{
//            redisCache.setCacheObject(key,1,10, TimeUnit.MINUTES);
//        }
        return Result.success(commentService.reply(dto));
    }


    /**
     * 根据id删除数据
     */
    @GetMapping("/delById")
    @Operation(summary = "deleteById", description = "删除数据")
    public Result<Integer> deleteById(@RequestParam("id") Long id) {
        return Result.success(commentService.delById(id));
    }

    /**
     * 根据id获取详情
     */
    @GetMapping("/getInfo")
    @Operation(summary = "getInfo", description = "根据id获取详情")
    public Result<CommentVo> getInfo(@RequestParam("id") Long id) {
        return Result.success(commentService.getInfo(id));
    }

    /**
     * 点赞
     */
    @GetMapping("/like")
    public Result<Integer> like(@RequestParam("id") Long id) {
        return Result.success(commentService.like(id, getUserId()));
    }

    @GetMapping("/disagree")
    public Result<Integer> disagree(@RequestParam("id") Long id) {
        return Result.success(commentService.disagree(id, getUserId()));
    }
}

