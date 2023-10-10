package com.xinshijie.wiki.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 世界年表
 * </p>
 *
 * @author 作者
 * @since 2023-09-07
 */
@Data
@Schema(name = "WorldChronologyFindDto", description = "世界年表 ")
public class WorldChronologyFindDto implements Serializable {

    private Integer wid;
    private String title;
    private Long page;

    private Long size;
}
