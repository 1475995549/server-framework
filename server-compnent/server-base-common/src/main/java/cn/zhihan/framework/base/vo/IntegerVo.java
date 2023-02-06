package cn.zhihan.framework.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "IntegerVo", description = "单个IntegerVo")
public class IntegerVo extends OneVo {
    
    @ApiModelProperty(value = "值")
    private Integer value;
    
    
}