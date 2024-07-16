package helloword.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import helloword.constant.Constant;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Timestamp2LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String text = jsonParser.getText();
        if(StringUtils.isEmpty(text)){
            return null;
        }
        try {
            long timestamp = Long.parseLong(text);
            return Instant.ofEpochMilli(timestamp).atZone(Constant.DEFAULT_ZONE_ID).toLocalDate();
        }catch (Exception e){
            //入参是字符串。
            return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }
}
