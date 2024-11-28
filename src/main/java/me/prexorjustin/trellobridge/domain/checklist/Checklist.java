package me.prexorjustin.trellobridge.domain.checklist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import me.prexorjustin.trellobridge.domain.TrelloModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Checklist extends TrelloModel {

    @Setter(AccessLevel.NONE)
    private String id;

    private String name, idBoard, idCard;
    private Integer pos;
    private List<ChecklistItem> checkItems;

}
