package cn.zhihan.framework.base.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.BooleanUtils;

import java.io.IOException;

/**
 * description: MyEnumSerializer
 * date: 2019/9/9 3:44 PM
 * version: 1.0
 * author: suzui
 */
public class MyBooleanSerializer extends JsonSerializer<Boolean> {
    
    @Override
    public void serialize(Boolean value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null == value) {
            jsonGenerator.writeString((String) null);
        }
        jsonGenerator.writeNumber(BooleanUtils.toIntegerObject(value));
    }
    
    
}
