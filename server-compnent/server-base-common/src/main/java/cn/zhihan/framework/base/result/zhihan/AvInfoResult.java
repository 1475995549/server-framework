package cn.zhihan.framework.base.result.zhihan;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: 音视频详情
 *
 * @date: 2019/12/5 15:37
 * version: 1.0
 * @author: liuzhenjun
 */
@Data
@NoArgsConstructor
public class AvInfoResult {

    /**
     * 时长
     */
    private String duration;
    /**
     * 大小
     */
    private String fileSize;
    /**
     * 码率
     */
    private String bitRate;

}
