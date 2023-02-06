package cn.zhihan.framework.base.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * description: MyPushDataForm
 *
 * @date: 2022/2/22 17:53
 * version: 1.0
 * @author: liuzhenjun
 */
@AllArgsConstructor
@lombok.Data
@ApiModel(value = "MyQuery", description = "推送数据form")
public class MyPushDataForm implements Serializable {

    @ApiModelProperty(value = "推送类型(100:url跳转,101:通知)")
    private String type;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "ios跳转地址")
    private String ios;
    @ApiModelProperty(value = "android跳转地址")
    private String android;

}
