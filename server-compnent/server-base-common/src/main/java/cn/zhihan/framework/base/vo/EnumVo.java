package cn.zhihan.framework.base.vo;

import cn.zhihan.framework.base.enums.MyEnum;
import cn.zhihan.framework.base.util.MyUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Data
@ApiModel(value = "EnumVo", description = "枚举Vo")
public class EnumVo extends OneVo {
    
    @ApiModelProperty(value = "编码")
    private Integer code;
    @ApiModelProperty(value = "描述")
    private String intro;
    
    public EnumVo(MyEnum myEnum) {
        this.code = myEnum.code();
        this.intro = myEnum.intro();
    }
    
    public static List<EnumVo> list(List<MyEnum> enums) {
        if (MyUtil.collectionEmpty(enums)) {
            return Collections.EMPTY_LIST;
        }
        return enums.stream().map(e -> new EnumVo(e)).collect(Collectors.toList());
    }
    
    public static List<EnumVo> list(MyEnum... enums) {
        if (enums == null || enums.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return list(Arrays.asList(enums));
    }
    
}