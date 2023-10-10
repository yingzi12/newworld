package com.xinshijie.wiki.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TurnstileVo {
    private boolean success;

    @JsonProperty("challenge_ts")
    private LocalDateTime challengeTs;

    private String hostname;

    @JsonProperty("error-codes")
    private List<String> errorCodes;

    private String action;
    private String cdata;
}
