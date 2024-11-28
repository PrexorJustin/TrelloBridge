package me.prexorjustin.trellobridge.domain.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CardCover {

    public String idAttachment, color, size, brightness, idPlugin, idUploadedBackground;
    private Boolean isTemplate;

}
