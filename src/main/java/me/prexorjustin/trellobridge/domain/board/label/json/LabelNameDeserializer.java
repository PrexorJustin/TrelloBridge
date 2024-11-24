package me.prexorjustin.trellobridge.domain.board.label.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.prexorjustin.trellobridge.domain.board.label.LabelColor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LabelNameDeserializer extends JsonDeserializer<Map<LabelColor, String>> {

    @Override
    public Map<LabelColor, String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        HashMap<LabelColor, String> result = new HashMap<>();

        var node = jsonParser.getCodec().readTree(jsonParser);

        node.fieldNames().forEachRemaining(fieldName -> {
            LabelColor labelColor = LabelColor.valueOf(fieldName.toUpperCase());
            String labelName = node.get(fieldName).asToken().asString();
            result.put(labelColor, labelName);
        });

        return result;
    }
}
