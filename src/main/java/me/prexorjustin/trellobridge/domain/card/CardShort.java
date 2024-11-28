package me.prexorjustin.trellobridge.domain.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.prexorjustin.trellobridge.domain.TrelloModel;

@EqualsAndHashCode(callSuper = true)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardShort extends TrelloModel {


    private String id, type, shortLink, text;

}
