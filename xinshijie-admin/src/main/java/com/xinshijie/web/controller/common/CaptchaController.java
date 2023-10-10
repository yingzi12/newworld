package com.xinshijie.web.controller.common;

import com.google.code.kaptcha.Producer;
import com.xinshijie.common.config.XinshijieConfig;
import com.xinshijie.common.constant.CacheConstants;
import com.xinshijie.common.constant.Constants;
import com.xinshijie.common.core.redis.RedisCache;
import com.xinshijie.common.enums.ResultCodeEnum;
import com.xinshijie.common.exception.ServiceException;
import com.xinshijie.common.utils.sign.Base64;
import com.xinshijie.common.utils.uuid.IdUtils;
import com.xinshijie.system.service.ISysConfigService;
import com.xinshijie.wiki.vo.CaptchaVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author xinshijie
 */
@Slf4j
@RestController
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService configService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public CaptchaVo getCode(HttpServletResponse response) throws IOException {
        CaptchaVo ajax = new CaptchaVo();
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        ajax.setCaptchaEnabled(captchaEnabled);
        if (!captchaEnabled) {
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = XinshijieConfig.getCaptchaType();
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheString(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            throw new ServiceException(ResultCodeEnum.SYSTEM_INNER_ERROR.getCode(), e.getMessage());
        }

        ajax.setUuid(uuid);
        ajax.setImg(Base64.encode(os.toByteArray()));
        return ajax;
    }
}
