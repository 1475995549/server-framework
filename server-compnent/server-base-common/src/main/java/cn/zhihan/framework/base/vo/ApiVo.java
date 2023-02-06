package cn.zhihan.framework.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "ApiVo", description = "接口Vo")
public class ApiVo extends OneVo {
    
    @ApiModelProperty(name = "apiId")
    private String apiId;
    @ApiModelProperty(name = "url")
    private String url;
    @ApiModelProperty(name = "ip")
    private String ip;
    @ApiModelProperty(name = "action")
    private String action;
    @ApiModelProperty(name = "method")
    private String method;
    @ApiModelProperty(name = "body")
    private String body;
    @ApiModelProperty(name = "header")
    private String header;
    @ApiModelProperty(name = "请求参数")
    private String param;
    @ApiModelProperty(name = "返回状态")
    private String status;
    @ApiModelProperty(name = "异常报告")
    private String exception;
    @ApiModelProperty(name = "返回结果")
    private String result;
    @ApiModelProperty(name = "开始时间")
    private Long startTime;
    @ApiModelProperty(name = "结束时间")
    private Long endTime;
    @ApiModelProperty(name = "用时(用时毫秒)")
    public Long time;
    @ApiModelProperty(value = "跟踪ID")
    private String requestId;
    
    @ApiModelProperty(name = "用户id")
    private Long userId;
    @ApiModelProperty(name = "用户token")
    private String userToken;
    @ApiModelProperty(name = "用户信息")
    private String userInfo;
    
    @ApiModelProperty(name = "是否异常")
    private Integer error;
    @ApiModelProperty(name = "是否模拟")
    private Integer mock;
    
    @ApiModelProperty(name = "运行环境")
    private String env;
    
}
