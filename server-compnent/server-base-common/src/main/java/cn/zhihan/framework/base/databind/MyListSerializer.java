package cn.zhihan.framework.base.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * description: MyListSerializer
 * date: 2019/9/9 3:44 PM
 * version: 1.0
 * author: suzui
 */
public class MyListSerializer extends JsonSerializer<List> {
    
    @Override
    public void serialize(List value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        if (null != value) {
            for (Object o : value) {
                jsonGenerator.writeObject(o);
            }
        }
        jsonGenerator.writeEndArray();
    }
}