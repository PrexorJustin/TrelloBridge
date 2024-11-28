package me.prexorjustin.trellobridge.domain.label;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import me.prexorjustin.trellobridge.domain.TrelloModel;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Label extends TrelloModel {

    private static final Set<String> VALID_COLORS = Set.of(
            "yellow", "purple", "blue", "red", "green", "orange", "black", "sky", "pink", "lime"
    );

    @Setter(AccessLevel.NONE)
    private String id;

    private String idBoard, name, color;
    private Integer uses;

    private Label(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public static Label build(String name, String color) {
        if (!VALID_COLORS.contains(color)) throw new IllegalArgumentException("Invalid color: " + color);
        return new Label(name, color);
    }

}
