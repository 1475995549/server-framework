package cn.zhihan.framework.base.databind;

import cn.zhihan.framework.base.enums.MyEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

/**
 * description: MyEnumDeserializer
 * date: 2019/9/9 3:44 PM
 * version: 1.0
 * author: suzui
 */
public class MyEnumDeserializer extends JsonDeserializer<MyEnum> {
    
    @Override
    public MyEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String codeValue = node.asText();//接收的当前枚举类code
        String currentName = jsonParser.getCurrentName();//枚举在当前对象内的字段名
        Object currentValue = jsonParser.getCurrentValue();//当前对象
        @SuppressWarnings("rawtypes")
        Class enumClazz = BeanUtils.findPropertyType(currentName, currentValue.getClass());
        if (enumClazz.isEnum()) {
            int code = Integer.parseInt(codeValue);
            MyEnum[] enumConstants = (MyEnum[]) enumClazz.getEnumConstants();
            for (MyEnum e : enumConstants) {
                if (e.code() == code) {
                    return e;
                }
            }
        }
        return null;
    }
}
