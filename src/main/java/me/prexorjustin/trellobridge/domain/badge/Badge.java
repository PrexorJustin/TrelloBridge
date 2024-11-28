package me.prexorjustin.trellobridge.domain.badge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import me.prexorjustin.trellobridge.domain.card.limits.CardLimits;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Badge {

    private String fogbugz;
    private Boolean location, viewingMemberVoted, subscribed, description, dueComplete;
    private Integer votes, checkItems, checkItemsChecked, comments, attachments;
    private Date due, start;

}
