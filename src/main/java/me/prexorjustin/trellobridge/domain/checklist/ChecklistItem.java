package me.prexorjustin.trellobridge.domain.checklist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistItem {

    @Setter(AccessLevel.NONE)
    private String id;

    private String name, state, dueReminder, idMember, idChecklist;
    private Integer pos;

    private Date due;

}
