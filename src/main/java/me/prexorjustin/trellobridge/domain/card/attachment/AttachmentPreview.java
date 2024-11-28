package me.prexorjustin.trellobridge.domain.card.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class AttachmentPreview {

    private String id, _id, url;
    private Boolean scaled;
    private Integer bytes, height, width;

}
