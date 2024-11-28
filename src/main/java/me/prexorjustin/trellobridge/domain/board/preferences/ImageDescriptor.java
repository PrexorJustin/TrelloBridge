package me.prexorjustin.trellobridge.domain.board.preferences;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDescriptor {

    private Integer width, height;
    private String url;

}
