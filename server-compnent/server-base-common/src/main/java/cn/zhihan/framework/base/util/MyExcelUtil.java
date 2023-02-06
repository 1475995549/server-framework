package cn.zhihan.framework.base.util;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * description: Excel文件导入导出
 * version: 1.0
 *
 * @date: 2019/10/22 14:51
 * @author: liuzhenjun
 */
@Component
@Slf4j
public class MyExcelUtil {
    
    //protected static MyQiniuUtil qiniu;
    //
    //@Autowired
    //MyQiniuUtil _qiniu;
    
    //@PostConstruct
    //public void beforeInit() {
    //    qiniu = _qiniu;
    //}
    
    public static List<List<String>> read(File file) {
        return read(file, 0);
    }
    
    public static List<List<String>> read(File file, int sheetIndex) {
        String fileName = file.getAbsolutePath();
        List<Map<Integer, String>> data = EasyExcel.read(fileName).headRowNumber(0).sheet(sheetIndex).doReadSync();
        List<List<String>> dataList = new ArrayList<>();
        int maxCol = 0;
        for (Map<Integer, String> one : data) {
            List<String> list = new ArrayList<>();
            List<Integer> keys = new ArrayList<>(one.keySet());
            Collections.sort(keys);
            for (Integer index : keys) {
                String value = one.get(index);
                list.add(value == null ? "" : value);
            }
            dataList.add(list);
            maxCol = Math.max(maxCol, list.size());
        }
        for (List<String> line : dataList) {
            if (line.size() >= maxCol || line.isEmpty()) {
                continue;
            }
            int col = maxCol - line.size();
            for (int i = 0; i < col; ++i) {
                line.add("");
            }
        }
        return dataList;
    }
    
    public static void export(List<List<String>> lists, File file) {
        EasyExcel.write(file.getAbsolutePath()).sheet("data").doWrite(lists);
    }
    
    /**
     * description: 导入Excel
     * version: 1.0
     *
     * @param url 文件地址
     * @return java.util.List<java.util.List < java.lang.String>>
     * @date: 2019/10/22 15:10
     * @author: liuzhenjun
     */
    public static List<List<String>> read(String url) {
        return read(url, 0);
    }
    
    /**
     * description: 导入Excel
     * version: 1.0
     *
     * @param url        文件地址
     * @param sheetIndex
     * @return java.util.List<java.util.List < java.lang.String>>
     * @date: 2020/9/15 09:47
     * @author: liuzhenjun
     */
    public static List<List<String>> read(String url, int sheetIndex) {
        List<List<String>> dataList = new ArrayList<>();
        try {
            String suffix = url.endsWith(".xlsx") ? ".xlsx" : ".xls";
            File file = new File("/tmp/" + System.currentTimeMillis() + "_" + RandomStringUtils.randomNumeric(6) + suffix);
            org.apache.commons.io.FileUtils.copyURLToFile(new URL(url), file);
            dataList = read(file, sheetIndex);
        } catch (Exception e) {
            log.error("excel read error：", e);
        }
        return dataList;
    }
    
    /**
     * description: 导出Excel并上传到七牛(冗余非静态)
     * version: 1.0
     *
     * @param lists 数据集合
     * @return java.lang.String 文件下载地址
     * @date: 2019/10/22 14:48
     * @author: liuzhenjun
     */
    //@Deprecated
    //public String export(List<List<String>> lists) {
    //    String name = System.currentTimeMillis() + "_" + RandomStringUtils.randomNumeric(6);
    //    return export(lists, name);
    //}
    
    /**
     * description: 导出Excel并上传到七牛(冗余非静态)
     * version: 1.0
     *
     * @param lists 数据集合
     * @param name  文件名称
     * @return java.lang.String 文件下载地址
     * @date: 2019/10/22 14:48
     * @author: liuzhenjun
     */
    //@Deprecated
    //public String export(List<List<String>> lists, String name) {
    //    try {
    //        File file = new File("/tmp/" + name + ".xlsx");
    //        export(lists, file);
    //        return _qiniu.upload(file);
    //    } catch (Exception e) {
    //        log.error("excel export error：", e);
    //    }
    //    return "";
    //}
    
    /**
     * description:导出Excel并上传到七牛
     * version: 1.0
     *
     * @param lists 数据集合
     * @return java.lang.String
     * @date: 2020/9/15 10:16
     * @author: liuzhenjun
     */
    //public static String exportExcel(List<List<String>> lists) {
    //    String name = System.currentTimeMillis() + "_" + RandomStringUtils.randomNumeric(6);
    //    return exportExcel(lists, name);
    //}
    
    /**
     * description:导出Excel并上传到七牛
     * version: 1.0
     *
     * @param lists 数据集合
     * @param name  文件名称
     * @return java.lang.String
     * @date: 2020/9/15 10:16
     * @author: liuzhenjun
     */
    //public static String exportExcel(List<List<String>> lists, String name) {
    //    try {
    //        File file = new File("/tmp/" + name + ".xlsx");
    //        export(lists, file);
    //        return qiniu.upload(file);
    //    } catch (Exception e) {
    //        log.error("excel export error：", e);
    //    }
    //    return "";
    //}
}