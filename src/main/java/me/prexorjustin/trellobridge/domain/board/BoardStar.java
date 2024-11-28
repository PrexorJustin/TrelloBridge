package me.prexorjustin.trellobridge.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardStar {

    private String idBoard, _id;
    private Integer pos;

}
