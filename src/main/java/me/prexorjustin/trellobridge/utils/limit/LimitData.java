package me.prexorjustin.trellobridge.utils.limit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitData {

    private String status;
    private Integer disableAt, warnAt;

}
