package cn.zhihan.framework.base.enums;

import cn.zhihan.framework.base.databind.MyEnumDeserializer;
import cn.zhihan.framework.base.databind.MyEnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

@JsonDeserialize(using = MyEnumDeserializer.class)
@JsonSerialize(using = MyEnumSerializer.class)
public interface MyEnum<E extends MyEnum<E>> extends Serializable {
    
    //@JsonValue
    int code();
    
    String intro();
    
    //用于swagger参数
    @Override
    String toString();//return this.code + ":" + this.intro;
    
}
