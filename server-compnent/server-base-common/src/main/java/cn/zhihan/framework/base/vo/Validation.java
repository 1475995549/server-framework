package cn.zhihan.framework.base.vo;

import cn.zhihan.framework.base.enums.MyButtonType;
import cn.zhihan.framework.base.enums.MyValidationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Validation", description = "提示消息扩展")
public class Validation implements Serializable {
    
    @ApiModelProperty(name = "类型")
    private MyValidationType type;
    @ApiModelProperty(name = "标题")
    private String title;
    @ApiModelProperty(name = "内容")
    private String content;
    @ApiModelProperty(name = "取消按钮文案")
    private String cancelText;
    @ApiModelProperty(name = "取消按钮类型")
    private MyButtonType cancelType;
    @ApiModelProperty(name = "确认按钮文案")
    private String submitText;
    @ApiModelProperty(name = "确认按钮类型")
    private MyButtonType submitType;
    
    public Validation(String message) {
        if (StringUtils.isBlank(message)) {
            return;
        }
        if (!message.contains("|")) {
            if (message.startsWith("-")) {
                this.type = MyValidationType.HIDDEN;
                this.content = message.substring(1);
            } else {
                this.type = MyValidationType.TOAST;
                this.content = message;
            }
            return;
        }
        String[] messages = StringUtils.split(message, "|");
        if (messages.length != 4) {
            return;
        }
        this.type = MyValidationType.DIALOG;
        String title = messages[0].trim();
        String content = messages[1].trim();
        String cancel = messages[2].trim();
        String submit = messages[3].trim();
        if (StringUtils.isNotBlank(title)) {
            this.title = title;
        }
        if (StringUtils.isNotBlank(content)) {
            this.content = content;
        }
        if (StringUtils.isNotBlank(cancel)) {
            if (!cancel.contains(":")) {
                this.cancelText = cancel;
                this.cancelType = MyButtonType.CLOSE;
            } else {
                String[] cancels = StringUtils.split(cancel, ":");
                this.cancelText = cancels[0].trim();
                this.cancelType = MyButtonType.convert(cancels[1].trim());
            }
        }
        if (StringUtils.isNotBlank(submit)) {
            if (!submit.contains(":")) {
                this.submitText = submit;
                this.submitType = MyButtonType.CLOSE;
            } else {
                String[] submits = StringUtils.split(submit, ":");
                this.submitText = submits[0].trim();
                this.submitType = MyButtonType.convert(submits[1].trim());
            }
        }
    }
    
}