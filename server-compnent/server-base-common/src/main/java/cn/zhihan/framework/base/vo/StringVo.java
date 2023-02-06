package cn.zhihan.framework.base.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "StringVo",description = "单个StringVo")
public class StringVo extends OneVo {
    
    @ApiModelProperty(value = "值")
    private String value;
    
}