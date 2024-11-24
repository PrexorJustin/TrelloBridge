package me.prexorjustin.trellobridge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import me.prexorjustin.trellobridge.ITrelloBridge;

@Getter
public class TrelloModel {
    
    @JsonIgnore
    protected transient ITrelloBridge trelloBridge;

    @SuppressWarnings("unchecked")
    public <T extends TrelloModel> T setTrelloBridge(ITrelloBridge trelloBridge) {
        this.trelloBridge = trelloBridge;
        return (T) this;
    }

}
