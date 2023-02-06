package cn.zhihan.framework.base.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@ApiModel(value = "StatisticsItemV0", description = "统计单项vo")
public class StatisticsItemVo extends OneVo {
    
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "总数")
    private Integer total;
    
    public StatisticsItemVo(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }
    
    
}
