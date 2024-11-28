package me.prexorjustin.trellobridge.domain.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import me.prexorjustin.trellobridge.domain.TrelloModel;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloList extends TrelloModel {

    @Setter(AccessLevel.NONE)
    private String id;

    private String name, color, idBoard, type;
    private Float pos;
    private Boolean closed, subscribed;

    private TrelloList(String name) {
        this.name = name;
    }

    public static TrelloList build(String name) {
        return new TrelloList(name);
    }
}
