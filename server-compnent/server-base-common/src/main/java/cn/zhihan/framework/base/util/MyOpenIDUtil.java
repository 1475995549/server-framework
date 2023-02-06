package cn.zhihan.framework.base.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MyOpenIDUtil {
    public static String gen(long userID, long appID) {
        // 补位到12位
        String fillZeros = new DecimalFormat("000000").format(appID) + new DecimalFormat("000000").format(userID);
        // 倒序
        String reverse = StringUtils.reverse(fillZeros);
        // 转换
        int reversePart1 = Integer.parseInt(StringUtils.substring(reverse, 0, 3));
        int reversePart2 = Integer.parseInt(StringUtils.substring(reverse, 3, 6));
        int reversePart3 = Integer.parseInt(StringUtils.substring(reverse, 6, 9));
        int reversePart4 = Integer.parseInt(StringUtils.substring(reverse, 9, 12));
        int total = reversePart1 + reversePart2 + reversePart3 + reversePart4;
        Character factor1 = chars.get(total % chars.size());
        Character factor2 = chars.get((total / chars.size()) % chars.size());
        String convert = new StringBuffer().append(factor1).append(factor1)
                .append(chars.get(reversePart1 / chars.size())).append(chars.get(reversePart1 % chars.size()))
                .append(factor1).append(factor2).append(chars.get(reversePart2 / chars.size()))
                .append(chars.get(reversePart2 % chars.size())).append(factor2).append(factor1)
                .append(chars.get(reversePart3 / chars.size())).append(chars.get(reversePart3 % chars.size()))
                .append(factor2).append(factor2).append(chars.get(reversePart4 / chars.size()))
                .append(chars.get(reversePart4 % chars.size())).toString();
        return convert;
    }
    
    public static long readUser(String openID) {
        // 去除冗余信息
        char[] usefulPart1 = {openID.charAt(2), openID.charAt(3)};
        char[] usefulPart2 = {openID.charAt(6), openID.charAt(7)};
        char[] usefulPart3 = {openID.charAt(10), openID.charAt(11)};
        char[] usefulPart4 = {openID.charAt(14), openID.charAt(15)};
        // 转换
        String convert = new DecimalFormat("000")
                .format(chars.indexOf(usefulPart1[0]) * chars.size() + chars.indexOf(usefulPart1[1]))
                + new DecimalFormat("000")
                .format(chars.indexOf(usefulPart2[0]) * chars.size() + chars.indexOf(usefulPart2[1]))
                + new DecimalFormat("000")
                .format(chars.indexOf(usefulPart3[0]) * chars.size() + chars.indexOf(usefulPart3[1]))
                + new DecimalFormat("000")
                .format(chars.indexOf(usefulPart4[0]) * chars.size() + chars.indexOf(usefulPart4[1]));
        // 倒序
        String reverse = StringUtils.reverse(convert);
        // 读取
        long userId = Long.parseLong(StringUtils.substring(reverse, 6, 12));
        return userId;
    }
    
    public static long readApp(String openID) {
        // 去除冗余信息
        char[] usefulPart1 = {openID.charAt(2), openID.charAt(3)};
        char[] usefulPart2 = {openID.charAt(6), openID.charAt(7)};
        char[] usefulPart3 = {openID.charAt(10), openID.charAt(11)};
        char[] usefulPart4 = {openID.charAt(14), openID.charAt(15)};
        // 转换
        String convert = new DecimalFormat("000")
                .format(chars.indexOf(usefulPart1[0]) * chars.size() + chars.indexOf(usefulPart1[1]))
                + new DecimalFormat("000")
                .format(chars.indexOf(usefulPart2[0]) * chars.size() + chars.indexOf(usefulPart2[1]))
                + new DecimalFormat("000")
                .format(chars.indexOf(usefulPart3[0]) * chars.size() + chars.indexOf(usefulPart3[1]))
                + new DecimalFormat("000")
                .format(chars.indexOf(usefulPart4[0]) * chars.size() + chars.indexOf(usefulPart4[1]));
        // 倒序
        String reverse = StringUtils.reverse(convert);
        // 读取
        long userId = Long.parseLong(StringUtils.substring(reverse, 0, 6));
        return userId;
    }
    
    private static final List<Character> chars = new ArrayList<Character>() {
        {
            for (int i = 97; i < 123; i++) {
                add((char) i);
            }
            for (int i = 65; i < 91; i++) {
                add((char) i);
            }
            for (int i = 49; i < 58; i++) {
                add((char) i);
            }
        }
    };
    
    
}
