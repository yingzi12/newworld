package com.xinshijie.wiki.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 申请管理员
 * </p>
 *
 * @author 作者
 * @since 2023-02-06
 */
@Data
public class ApplyManageDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请人
     */
    private LocalDateTime applyTime;

    /**
     * 申请说明
     */
    private String applyExplain;

    /**
     * 申请人姓名
     */
    private String applyName;

    /**
     * 申请人id
     */
    private Long applyId;

    /**
     * 世界id
     */
    private Integer wid;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 审核说明
     */
    private String auditExplain;

    /**
     * 审核人
     */
    private String auditName;

    /**
     * 审核人id
     */
    private Long auditId;

    /**
     * 1 审核通过 0 待审核 2 失败
     */
    private Integer status;

    private LocalDateTime operatorTime;
}
