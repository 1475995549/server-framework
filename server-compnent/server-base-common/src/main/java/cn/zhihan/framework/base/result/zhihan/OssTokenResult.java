package cn.zhihan.framework.base.result.zhihan;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: OssTokenResult
 * date: 2019/8/26 16:35
 * version: 1.0
 * author: liuzhenjun
 */
@Data
@NoArgsConstructor
public class OssTokenResult {

    /**
     * 七牛凭证
     */
    private String uptoken;
    /**
     * 上传域名
     */
    private String domain;

}
