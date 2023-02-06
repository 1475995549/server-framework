package cn.zhihan.framework.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@ApiModel(value = "StatisticsCatalogVo", description = "统计类目vo")
public class StatisticsCatalogVo extends OneVo {
    
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "总数")
    private Integer total;
    @ApiModelProperty(value = "统计单项列表")
    private List<StatisticsItemVo> items;
    
    public StatisticsCatalogVo(String name, List<StatisticsItemVo> items) {
        this.name = name;
        this.amount = items.size();
        this.items = items;
    }
    
    
}
