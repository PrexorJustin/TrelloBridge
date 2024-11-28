package me.prexorjustin.trellobridge.domain.card.limits;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CardLimits {

    private CardLimitCategory attachments;
    private CardLimitCategory checklists;
    private CardLimitCategory stickers;

}
