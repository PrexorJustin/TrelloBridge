package me.prexorjustin.trellobridge.domain.board.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import me.prexorjustin.trellobridge.utils.limit.LimitData;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardLimitCategory {

    private LimitData perBoard;

}
