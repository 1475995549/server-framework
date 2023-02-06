package cn.zhihan.framework.base.databind;

import cn.zhihan.framework.base.enums.MyEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * description: MyEnumSerializer
 * date: 2019/9/9 3:44 PM
 * version: 1.0
 * author: suzui
 */
public class MyEnumSerializer extends JsonSerializer<MyEnum> {
    
    @Override
    public void serialize(MyEnum o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(o.code());
        String className = o.getClass().getSimpleName();
        jsonGenerator.writeStringField(className + "Intro", o.intro());
    }
    
    
}
