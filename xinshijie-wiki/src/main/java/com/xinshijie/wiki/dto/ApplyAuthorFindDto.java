package com.xinshijie.wiki.dto;


import com.xinshijie.common.core.domain.BaseEntity;
import lombok.Data;


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
public class ApplyAuthorFindDto extends BaseEntity  {

    /**
     * 申请人
     */
    private LocalDateTime applyTime;


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

    private Integer notStatus;

    /**
     * 小说id
     */
    private Long sid;


}
