package cn.zhihan.framework.base.result.zhihan;

import lombok.Data;

/**
 * description: ZhiHanResult
 *
 * @date: 2020/5/27 12:48
 * version: 1.0
 * @author: liuzhenjun
 */
@Data
public class ZhiHanResult<T> {
    private Integer code;
    private String message;
    private T data;
    public String status;
    public Long systemTime;

    public boolean isSuccess() {
        return this.code == 20000;
    }
}
