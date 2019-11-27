package ru.lanit.dto.desirializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;
import ru.lanit.dto.CarModel;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CarModelDeserialize extends JsonDeserializer {

    @Override
    public CarModel deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String data = jsonParser.getText().trim();
        Matcher matcher = Pattern.compile("^([A-z0-9_]+)-([A-z0-9_-]+)$").matcher(data);

        if(!matcher.find()){
            context.handleUnexpectedToken(this.getClass(),jsonParser);
            return null;
        }

        String vendor = matcher.group(1);
        String model = matcher.group(2);
        return new CarModel(model,vendor);
    }
}
