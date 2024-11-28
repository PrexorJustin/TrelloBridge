package me.prexorjustin.trellobridge.domain.board.plugin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.TrelloModel;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardPlugin extends TrelloModel {

    private String id, idBoard, idPlugin;
    private Boolean promotional;

}
