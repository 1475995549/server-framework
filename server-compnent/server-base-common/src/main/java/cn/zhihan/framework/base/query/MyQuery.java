package cn.zhihan.framework.base.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * description: MyQuery
 * date: 2019/10/17 4:34 PM
 * version: 1.0
 * author: suzui
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@lombok.Data
@ApiModel(value = "MyQuery", description = "基础query")
public class MyQuery implements Serializable {
    
    public static final Integer PAGE = 1;
    public static final Integer SIZE = 10;
    public static final Integer SIZE_MAX = Integer.MAX_VALUE;
    
    //field字段名 用于field查询
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    
    //order查询条件 用于fetch查询
    public static final String ORDER_ID_ASC = " id asc";
    public static final String ORDER_ID_DESC = " id desc";
    
    @ApiModelProperty(value = "模糊查询", position = 0)
    private String search;
    @ApiModelProperty(value = "开始时间", position = 1)
    private Long fromTime;
    @ApiModelProperty(value = "截止时间", position = 2)
    private Long toTime;
    @ApiModelProperty(value = "分页页码", position = 3)
    private Integer page;
    @ApiModelProperty(value = "分页条数", position = 4)
    private Integer size;
    @ApiModelProperty(value = "排序条件", hidden = true)
    private String condition;
    
    public MyQuery() {
        this.page = PAGE;
        this.size = SIZE_MAX;
    }
    
    public Integer page() {
        return this.page != null ? this.page : PAGE;
    }
    
    public Integer size() {
        return this.size != null ? this.size : SIZE_MAX;
    }
    
    public void condition(String condition) {
        this.condition = condition;
    }
    
    public String condition() {
        return this.condition;
    }
    
    //long类型id 只查null值 传-1
    public Boolean withNull(Long v) {
        return v.intValue() == -1;
    }
    
    //long类型id 只查非null值 传-2
    public Boolean withNotNull(Long v) {
        return v.intValue() == -2;
    }
    
    //int类型数值 只查null值 传-1
    public Boolean withNull(Integer v) {
        return v.intValue() == -1;
    }
    
    //int类型数值 只查非null值 传-2
    public Boolean withNotNull(Integer v) {
        return v.intValue() == -2;
    }
    
}
