package cn.zhihan.framework.base.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@lombok.Data
@ApiModel(value = "MyForm", description = "基础form")
public class MyForm implements Serializable {

    
}
