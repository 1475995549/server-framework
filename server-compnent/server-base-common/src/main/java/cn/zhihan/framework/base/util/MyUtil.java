package cn.zhihan.framework.base.util;

import cn.zhihan.framework.base.constant.MyConstant;
import cn.zhihan.framework.base.enums.MyEnum;
import cn.zhihan.framework.base.vo.PageVo;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description: MyUtil
 * date: 2019/9/7 12:27 PM
 * version: 1.0
 * author: suzui
 */
public class MyUtil {
    
    public static Boolean collectionEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
    
    public static Boolean collectionNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }
    
    public static List page(List list, int page, int size, Comparator comparator) {
        if (collectionEmpty(list)) {
            return new ArrayList<>();
        }
        int from = (page - 1) * size;
        int to = Math.min(list.size(), page * size);
        if (from >= list.size()) {
            return new ArrayList<>();
        }
        if (comparator != null) {
            list.sort(comparator);
        }
        return list.subList(from, to);
    }
    
    public static List page(List list, int page, int size) {
        return page(list, page, size, null);
    }
    
    public static PageVo pageVo(List list, int page, int size) {
        return pageVo(list, page, size, null);
    }
    
    public static PageVo pageVo(List list, int page, int size, Comparator comparator) {
        if (MyUtil.collectionEmpty(list)) {
            return new PageVo(page, size, 0, new ArrayList());
        }
        return new PageVo(page, size, list.size(), page(list, page, size, comparator));
    }
    
    private static String jsonToJoin(String json) {
        if (StringUtils.isBlank(json)) {
            return json;
        }
        if (json.startsWith("[") && json.endsWith("]")) {
            return json.substring(1, json.length() - 1);
        }
        return json;
    }
    
    public static List<String> strToList(String string) {
        if (StringUtils.isBlank(string)) {
            return Lists.list();
        }
        return Lists.list(StringUtils.split(jsonToJoin(string), ","));
    }
    
    public static List<Long> idsToList(String ids) {
        if (StringUtils.isBlank(ids)) {
            return Lists.list();
        }
        return Arrays.stream(StringUtils.split(jsonToJoin(ids), ",")).map(id -> Long.parseLong(id)).collect(Collectors.toList());
    }
    
    public static List<Integer> intsToList(String ints) {
        if (StringUtils.isBlank(ints)) {
            return Lists.list();
        }
        return Arrays.stream(StringUtils.split(jsonToJoin(ints), ",")).map(i -> Integer.parseInt(i)).collect(Collectors.toList());
    }
    
    public static List<Integer> codesToList(String codes) {
        if (StringUtils.isBlank(codes)) {
            return Lists.list();
        }
        return Arrays.stream(StringUtils.split(jsonToJoin(codes), ",")).map(code -> Integer.parseInt(code)).collect(Collectors.toList());
    }
    
    public static String join(Object[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        return StringUtils.join(array, ",");
    }
    
    public static String join(Collection collection) {
        if (collectionEmpty(collection)) {
            return "";
        }
        return StringUtils.join(collection, ",");
    }
    
    public static String listToSql(List<String> list) {
        if (collectionEmpty(list)) {
            return "";
        }
        return list.stream().map(s -> "'" + s + "'").collect(Collectors.joining(","));
    }
    
    public static <T> Map<Long, T> listToIdMap(List<T> list) {
        return listToIdMap(list, MyConstant.FIELD_ID);
    }
    
    public static <T> Map<Long, T> listToIdMap(List<T> list, String key) {
        Map<Long, T> keyMap = new HashMap<>();
        list.forEach(o -> {
            Map<String, Object> map = MyJsonUtil.read(MyJsonUtil.write(o), Map.class);
            keyMap.put(Long.valueOf(map.get(key).toString()), o);
        });
        return keyMap;
    }
    
    public static <T> Map<String, T> listToNameMap(List<T> list) {
        return listToNameMap(list, MyConstant.FIELD_NAME);
    }
    
    public static <T> Map<String, T> listToNameMap(List<T> list, String key) {
        Map<String, T> keyMap = new HashMap<>();
        list.forEach(o -> {
            Map<String, Object> map = MyJsonUtil.read(MyJsonUtil.write(o), Map.class);
            keyMap.put((String) map.get(key), o);
        });
        return keyMap;
    }
    
    public static boolean isPhoneLegal(String phone) {
        String regExp = "^(1)\\d{10}$";
        return StringUtils.isNotBlank(phone) && phone.matches(regExp);
    }
    
    public static boolean isEmailLegal(String email) {
        String regExp = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9]+(.[a-zA-Z]{2,4}){1,4}";
        return StringUtils.isNotBlank(email) && email.matches(regExp);
    }
    
    public static boolean isNone(String string) {
        return string == null || string.equalsIgnoreCase("null") || string.equalsIgnoreCase("(null)") || string.equalsIgnoreCase("undefined") || string.equalsIgnoreCase("NaN");
    }
    
    public static boolean isBlank(String string) {
        return StringUtils.isBlank(string) || string.equalsIgnoreCase("null") || string.equalsIgnoreCase("(null)") || string.equalsIgnoreCase("undefined") || string.equalsIgnoreCase("NaN");
    }
    
    public static String genURL(String url, Object... params) {
        return String.format(url, params);
    }
    
    public Boolean toBooleanObject(Integer i) {
        return BooleanUtils.toBooleanObject(i);
    }
    
    public Integer toIntegerObject(Boolean b) {
        return BooleanUtils.toIntegerObject(b);
    }
    
    public static List<String[]> enums(Class clazz) {
        try {
            Method method = clazz.getMethod("values");
            MyEnum[] values = (MyEnum[]) method.invoke(null, null);
            List<String[]> list = new ArrayList<>();
            for (MyEnum value : values) {
                list.add(new String[]{value.code() + "", value.intro()});
            }
            return list;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    /**
     * 去除非ascii码字符
     *
     * @param str
     * @return
     */
    public static String removeNonAscii(String str) {
        return str.replaceAll("[^\\x00-\\x7F]", "");
    }
    
    /**
     * 去除不可打印字符
     *
     * @param str
     * @return
     */
    public static String removeNonPrintable(String str) {
        return str.replaceAll("[\\p{C}]", "");
    }
    
    /**
     * 去除一些控制字符 Control Char
     *
     * @param str
     * @return
     */
    public static String removeSomeControlChar(String str) {
        return str.replaceAll("[\\p{Cntrl}\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]", ""); // Some Control Char
    }
    
    /**
     * 去除一些换行制表符
     *
     * @param str
     * @return
     */
    public static String removeFullControlChar(String str) {
        return removeNonPrintable(str).replaceAll("[\\r\\n\\t]", "");
    }
    
    public static char toUpperCase(char chars) {
        if (97 <= chars && chars <= 122) {
            chars -= 32;
        }
        return chars;
    }
    
    public static char toLowerCase(char chars) {
        if (65 <= chars && chars <= 90) {
            chars += 32;
        }
        return chars;
    }
    
    
}
