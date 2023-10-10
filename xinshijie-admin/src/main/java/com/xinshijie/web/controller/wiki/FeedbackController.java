package com.xinshijie.web.controller.wiki;

import com.xinshijie.common.constant.CacheConstants;
import com.xinshijie.common.core.controller.BaseController;
import com.xinshijie.common.core.domain.model.LoginUser;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.core.vo.Result;
import com.xinshijie.common.exception.user.CaptchaException;
import com.xinshijie.common.exception.user.CaptchaExpireException;
import com.xinshijie.common.utils.StringUtils;
import com.xinshijie.wiki.domain.Feedback;
import com.xinshijie.wiki.dto.FeedbackDto;
import com.xinshijie.wiki.service.IFeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * <p>
 * 意见反馈 前端控制器
 * </p>
 *
 * @author zx
 * @since 2022-09-05
 */
@Slf4j
@RestController
@Tag(name = "FeedbackController", description = "意见反馈")
@RequestMapping("/wiki/feedback")
public class FeedbackController extends BaseController {
    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 新增数据
     */
    @PostMapping("/add")
    @Operation(summary = "新增意见反馈", description = "新增意见反馈")
    public Result<Feedback> add(@RequestBody FeedbackDto dto) {
        LoginUser loginUser = getLoginUserNull();
        if (loginUser != null) {
            dto.setCreateId(loginUser.getUserId());
        }
        dto.setCreateTime(LocalDateTime.now());
        validateCaptcha(dto.getCode(), dto.getUuid());
        return Result.success(feedbackService.add(dto));
    }

    /**
     * 校验验证码
     *
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheString(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
    }
}

