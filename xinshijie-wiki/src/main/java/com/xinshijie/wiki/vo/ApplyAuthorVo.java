package com.xinshijie.wiki.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 申请作者
 * </p>
 *
 * @author 作者
 * @since 2023-02-06
 */
@Data
public class ApplyAuthorVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long applyId;

    /**
     * 世界id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long wid;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long auditId;

    /**
     * 1 审核通过 0 待审核 2 失败
     */
    private Integer status;

    /**
     * 小说id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sid;
    private LocalDateTime operatorTime;
}
