package ru.lanit.dto.desirializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserialize extends JsonDeserializer {

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String data = jsonParser.getText().trim();
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
