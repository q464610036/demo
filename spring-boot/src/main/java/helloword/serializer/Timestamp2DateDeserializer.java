package helloword.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import helloword.util.DateUtil;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * 时间字符串、时间戳序列化为Date
 */
public class Timestamp2DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String text = jsonParser.getText();
        if(StringUtils.isEmpty(text)){
            return null;
        }
        try {
            long timestamp = Long.parseLong(text);
            return new Date(timestamp);
        }catch (Exception e){
            //入参是字符串。
            if (text.length() == 10) {
                return DateUtil.StringToDate(text, DateUtil.DAY_FORMAT);
            } else {
                return DateUtil.StringToDate(text, DateUtil.TIME_FORMAT);
            }
        }
    }
}
