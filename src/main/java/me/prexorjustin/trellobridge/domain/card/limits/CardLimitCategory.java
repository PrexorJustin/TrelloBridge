package me.prexorjustin.trellobridge.domain.card.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import me.prexorjustin.trellobridge.utils.limit.LimitData;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CardLimitCategory {

    private LimitData perBoard;
    private LimitData perCard;

}
