package helloword.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import helloword.constant.Constant;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Time2TimestampSerializer extends JsonSerializer {

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(o == null){
            return;
        }
        if(o instanceof LocalDate){
            LocalDate localDate = (LocalDate) o;
            jsonGenerator.writeString(String.valueOf(localDatetime2Timestamp(localDate.atStartOfDay())));
        }else if(o instanceof LocalDateTime){
            LocalDateTime localDateTime = (LocalDateTime) o;
            jsonGenerator.writeString(String.valueOf(localDatetime2Timestamp(localDateTime)));
        }else{
            throw new RuntimeException("un supported type：[" + o.getClass().getName() + "]");
        }
    }

    /**
     * LocalDateTime转时间戳
     * @author 晾、小生
     * @since 2022/11/28 17:21
     */
    private Long localDatetime2Timestamp(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of(Constant.DEFAULT_ZONE_OFFSET)).toEpochMilli();
    }
}
