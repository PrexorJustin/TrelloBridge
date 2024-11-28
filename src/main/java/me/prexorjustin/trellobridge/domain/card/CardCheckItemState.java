package me.prexorjustin.trellobridge.domain.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardCheckItemState {

    private String idCheckItem;
    private String state;

}
