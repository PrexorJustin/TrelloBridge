package me.prexorjustin.trellobridge.domain.customfield;

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
public class CustomField extends TrelloModel {

    @Setter(AccessLevel.NONE)
    private String id;

    private String idModel, modelType, fieldGroup, type, name;
    private Integer pos;
    private Boolean isSuggestedField;
    private Display display;
    private List<Option> options;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Display {

        private Boolean cardFront;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Option {

        @Setter(AccessLevel.NONE)
        private String id;

        private String idCustomField, color;
        private Integer pos;
        private Value value;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Value {

            private String text;

        }
    }
}
