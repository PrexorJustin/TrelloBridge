package me.prexorjustin.trellobridge.domain.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.TrelloModel;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListShort extends TrelloModel {

    private String id, type, text;

}
