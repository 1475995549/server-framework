package cn.zhihan.framework.base.result.zhihan;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: ScreenShotResult
 * date: 2019-08-20 09:47
 * version: 1.0
 * author: chenzhongyi
 */
@Data
@NoArgsConstructor
public class ScreenShotResult {

    /**
     * 持久化数据处理ID
     */
    private Long pfopId;
    /**
     * 持久化处理ID
     */
    private String persistentId;
    /**
     * key
     */
    private String inputKey;
    /**
     * 文件格式
     */
    private String format;
    /**
     * 持久化key
     */
    private String pfopKey;
    /**
     * 持久化格式
     */
    private String pfopFormat;
    /**
     * 持久化url
     */
    private String pfopUrl;
    /**
     * 返回结果
     */
    private String result;
    /**
     * 宽
     */
    private Integer width;
    /**
     * 高
     */
    private Integer height;
    /**
     * 视频时刻
     */
    private Integer offset;
    /**
     * 处理类型|Integer|[[100,视频],[101,音频],[102,图片]]
     */
    private Integer type;
    /**
     * 状态|Integer|[[100,待处理],[101,处理中],[102,失败],[103,成功]]
     */
    private Integer status;
    /**
     * 供应商|Integer|[[100,七牛]]
     */
    private Integer supplier;
    /**
     * 资源获取地址
     */
    private String url;
}
