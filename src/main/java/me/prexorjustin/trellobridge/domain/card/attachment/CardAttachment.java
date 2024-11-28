package me.prexorjustin.trellobridge.domain.card.attachment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardAttachment {

    private String id;

    private String edgeColor, idMember, mimeType, name, url, fileName;
    private Integer bytes, pos;
    private Date date;
    private Boolean isUpload;
    List<AttachmentPreview> previews;

}
