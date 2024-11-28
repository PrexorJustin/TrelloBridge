package me.prexorjustin.trellobridge.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.TrelloModel;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardShort extends TrelloModel {

    private String id, name, shortLink;

}
