package me.prexorjustin.trellobridge.domain.board.label.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;
import me.prexorjustin.trellobridge.domain.board.label.LabelColor;

import java.io.IOException;
import java.util.Map;

public class LabelNameSerialize extends JsonSerializer<Map<LabelColor, String>> {

    @Override
    public void serialize(Map<LabelColor, String> labelColorStringMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        labelColorStringMap.forEach((key, value) -> {
            try {
                jsonGenerator.writeStringField(key.name().toLowerCase(), value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
