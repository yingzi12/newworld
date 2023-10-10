package com.xinshijie.wiki.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "sid")
public class ChapterAuditDto {
    @Schema(description = "sid")
    private Long sid;

    private Long dscid;

    @Schema(description = "审核状态 0不通过 1通过")
    private Integer auditStatus;

    @Schema(description = "审核原因编号")
    private Integer auditNumber;

    @Schema(description = "审核说明")
    private String auditContent;
}
