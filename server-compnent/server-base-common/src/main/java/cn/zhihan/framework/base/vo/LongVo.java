package cn.zhihan.framework.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "LongVo", description = "单个LongVo")
public class LongVo extends OneVo {
    
    @ApiModelProperty(value = "值")
    private Long value;
    
    
}