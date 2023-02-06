package cn.zhihan.framework.base.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyQRCodeUtil {
    
    //protected static MyQiniuUtil qiniuUtil;
    //
    //@Autowired
    //private MyQiniuUtil _qiniuUtil;
    
    //@PostConstruct
    //public void beforeInit() {
    //    qiniuUtil = _qiniuUtil;
    //}
    
    public static void gen(String filePath, String logoPath, String content) {
        try {
            int width = 250; // 图像宽度
            int height = 250; // 图像高度
            String format = "jpg";// 图像类型
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");//编码
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//容错
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageConfig config = new MatrixToImageConfig();
            MatrixToImageWriter.writeToPath(bitMatrix, format, path, config);// 输出图像
            
            if (StringUtils.isBlank(logoPath)) {
                return;
            }
            BufferedImage image = ImageIO.read(new File(filePath));
            Graphics2D g = image.createGraphics();
            //logo起始位置，此目的是为logo居中显示
            int w = width / 5;
            int h = height / 5;
            int x = (width - w) / 2;
            int y = (height - h) / 2;
            //绘制图
            BufferedImage logo = ImageIO.read(new File(logoPath));
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(
                    logo.getScaledInstance(w, h, Image.SCALE_SMOOTH),
                    x, y, null);
            
            //给logo画边框
            //构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
            g.setStroke(new BasicStroke(1));
            g.setColor(Color.RED);
            g.drawRect(x, y, w, h);
            
            g.dispose();
            //写入logo照片到二维码
            ImageIO.write(image, format, new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static File gen(String content) {
        String filePath = "/tmp/qrcode" + RandomStringUtils.randomNumeric(16) + ".jpg";
        gen(filePath, "", content);
        return new File(filePath);
    }
    
    /**
     * description: 生成并上传到七牛
     * version: 1.0
     *
     * @param content
     * @return java.lang.String
     * @date: 2020/8/24 09:48
     * @author: liuzhenjun
     */
    //public static String genUrl(String content) {
    //    File file = gen(content);
    //    return qiniuUtil.upload(file);
    //}
    
}

