package cn.zhihan.framework.base.util;

import cn.zhihan.framework.base.enums.MyEnum;
import cn.zhihan.framework.base.databind.MyBooleanDeserializer;
import cn.zhihan.framework.base.databind.MyBooleanSerializer;
import cn.zhihan.framework.base.databind.MyEnumDeserializer;
import cn.zhihan.framework.base.databind.MyEnumSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * description: MyJsonUtil
 * date: 2019/9/9 3:38 PM
 * version: 1.0
 * author: suzui
 */
@Slf4j
public class MyJsonUtil {
    
    public static final ObjectMapper mapper = new ObjectMapper();
    
    static {
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        SimpleModule boolModule = new SimpleModule();
        boolModule.addDeserializer(Boolean.class, new MyBooleanDeserializer());
        boolModule.addSerializer(Boolean.class, new MyBooleanSerializer());
        
        //SimpleModule listModule = new SimpleModule();
        //listModule.addSerializer(List.class, new MyListSerializer());
        
        SimpleModule enumModule = new SimpleModule();
        enumModule.addDeserializer(MyEnum.class, new MyEnumDeserializer());
        enumModule.addSerializer(MyEnum.class, new MyEnumSerializer());
        
        //for spring admin 拆分base-component 暂时注释
        //SimpleModule registrationModule = new SimpleModule();
        //registrationModule.addSerializer(Registration.class, ToStringSerializer.instance);
        //registrationModule.addDeserializer(Registration.class, new RegistrationDeserializer());
        
        mapper.registerModule(boolModule).registerModule(enumModule);//.registerModule(registrationModule);//.registerModule(listModule);
//        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//                String fieldName = gen.getOutputContext().getCurrentName();
//                try {
//                    //反射获取字段类型
//                    Field field = gen.getCurrentValue().getClass().getDeclaredField(fieldName);
//                    if (Objects.equals(field.getType(), List.class)) {
//                        //列表型空值返回[]
//                        gen.writeStartArray();
//                        gen.writeEndArray();
//                        return;
//                    }
//                } catch (NoSuchFieldException e) {
//                }
//                //默认返回""
//                gen.writeNull();
//            }
//        });
    
    }
    
    private MyJsonUtil() {
    }
    
    
    public static String write(Object obj) {
        try {
            String result = mapper.writeValueAsString(obj);
            return result;
        } catch (JsonProcessingException ex) {
            log.error("failed serialize object to json string: {}", ex);
        }
        return null;
    }
    
    
    public static <T> T read(String jsonStr, Class<T> cls) {
        T object = null;
        try {
            object = mapper.readValue(jsonStr, cls);
        } catch (IOException ex) {
            log.error("failed deserialize string: {} to Class {}'s instance, error: {}", jsonStr,
                    cls.getName(), ex);
        }
        return object;
    }
    
    
}
