package me.prexorjustin.trellobridge.domain.board.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardLimits {

    private BoardLimitCategory attachments;

}
