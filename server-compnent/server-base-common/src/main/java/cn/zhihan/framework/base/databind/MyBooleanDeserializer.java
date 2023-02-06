package cn.zhihan.framework.base.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * description: MyBooleanDeserializer
 * date: 2019/9/9 3:44 PM
 * version: 1.0
 * author: suzui
 */
public class MyBooleanDeserializer extends JsonDeserializer<Boolean> {
    
    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getValueAsString();
        if (StringUtils.isBlank(value)) {
            return null;
        }
        if (StringUtils.equals("0", value) || StringUtils.equalsIgnoreCase("false", value) || StringUtils.equalsIgnoreCase("no", value) || StringUtils.equalsIgnoreCase("n", value)) {
            return Boolean.FALSE;
        }
        if (StringUtils.equals("1", value) || StringUtils.equalsIgnoreCase("true", value) || StringUtils.equalsIgnoreCase("yes", value) || StringUtils.equalsIgnoreCase("y", value)) {
            return Boolean.TRUE;
        }
        //getBoolean有异常可以抛出
        return Boolean.getBoolean(value);
    }
}
