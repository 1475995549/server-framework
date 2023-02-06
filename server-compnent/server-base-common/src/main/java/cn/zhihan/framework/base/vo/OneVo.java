package cn.zhihan.framework.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@ApiModel(value = "OneVo", description = "单个vo")
//OneVo中字段子类中禁止再次定义 引起beanutil转换赋值 declares multiple
public class OneVo extends DataVo {

    @JsonProperty("id")
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "创建时间")
    private Long createTime;
    @ApiModelProperty(value = "更新时间")
    private Long updateTime;
    @ApiModelProperty(value = "序号")
    private Integer sequence;
    @ApiModelProperty(value = "校验标识")
    private Integer validation;
    
    public OneVo(Long id) {
        this.id = id;
    }
    
    @Deprecated
    public void preId() {
        if (this.id != null) {
            return;
        }
        String fieldName = this.getClass().getSimpleName().replace("Vo", "").toLowerCase() + "Id";
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            // 对象的属性的访问权限设置为可访问
            field.setAccessible(true);
            // 获取属性的对应的值
            this.id = Long.parseLong(field.get(this).toString());
        } catch (Exception e) {
            throw new RuntimeException("id init error");
        }
    }
    
    @Deprecated
    public void preSelfId() {
        if (this.id == null) {
            return;
        }
        String fieldName = this.getClass().getSimpleName().replace("Vo", "").toLowerCase() + "Id";
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            // 对象的属性的访问权限设置为可访问
            field.setAccessible(true);
            // 设置属性的对应的值
            field.set(this, this.id);
        } catch (Exception e) {
            throw new RuntimeException("id init error");
        }
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getClass().hashCode();
        result = prime * result + this.id.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        OneVo one = (OneVo) obj;
        if (this.id.equals(one.id)) {
            return true;
        }
        return false;
    }
    
    
}
