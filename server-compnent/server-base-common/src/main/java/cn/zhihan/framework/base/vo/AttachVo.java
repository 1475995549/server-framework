package cn.zhihan.framework.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AttachVo", description = "附件Vo")
public class AttachVo extends OneVo {

    @ApiModelProperty(value = "链接地址")
    public String url;
    @ApiModelProperty(value = "文件名")
    public String name;
    @ApiModelProperty(value = "大小")
    public Long fileSize;
    @ApiModelProperty(value = "后缀")
    public String suffix;

}
