package cn.zhihan.framework.base.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

/**
 * description: MyUtil1218
 * date: 2021/12/18 6:34 下午
 * version: 1.0
 * author: suzui
 */
public class MyUtil1218 {
    
    public static void main(String[] args) {
        List<List<String>> data = MyExcelUtil.read(new File("/Users/suzui/Downloads/tmp/TSR_Stkstat.xlsx"));
        List<List<String>> lists = new ArrayList<>();
        List<String> title = data.get(0);
        data.remove(0);
        for (List<String> list : data) {
            if (list.get(5).equals("9:00")) {
                list.set(5, "9:30");
            }
            //if (StringUtils.isBlank(list.get(6))) {
            //    list.set(6, "2021/05/01");
            //    list.set(7, "9:30");
            //}
            Date start = MyDateUtil.format(list.get(4) + " " + list.get(5), "yyyy/MM/dd HH:mm");
            Date end = MyDateUtil.format(list.get(6) + " " + list.get(7), "yyyy/MM/dd HH:mm");
            list.set(4, MyDateUtil.format(start, "yyyyMMdd"));
            list.set(5, MyDateUtil.format(start, "HHmm"));
            list.set(6, MyDateUtil.format(end, "yyyyMMdd"));
            list.set(7, MyDateUtil.format(end, "HHmm"));
        }
        
        for (List<String> list : data) {
            if (StringUtils.isBlank(list.get(6))) {
                continue;
            }
            Date start = MyDateUtil.format(list.get(4) + " " + list.get(5), "yyyyMMdd HHmm");
            Date end = MyDateUtil.format(list.get(6) + " " + list.get(7), "yyyyMMdd HHmm");
            int day = MyDateUtil.dayBetween(start.getTime(), end.getTime());
            if (day == 0) {
                List<String> l = new ArrayList<>(list);
                lists.add(l);
                continue;
            }
            if (day >= 1) {
                for (int i = 0; i <= day; i++) {
                    List<String> l = new ArrayList<>(list);
                    if (i == 0) {
                        l.set(6, l.get(4));
                        l.set(7, "1500");
                        lists.add(l);
                        continue;
                    }
                    if (i == day) {
                        l.set(4, l.get(6));
                        l.set(5, "0930");
                        lists.add(l);
                        continue;
                    }
                    if (i > 0 && i < day) {
                        String date = MyDateUtil.format(MyDateUtil.addDays(start, i), "yyyyMMdd");
                        l.set(4, date);
                        l.set(5, "0930");
                        l.set(6, date);
                        l.set(7, "1500");
                        lists.add(l);
                        continue;
                    }
                }
            }
        }
        Map<String, List<String>> map = new LinkedHashMap<>();
        map.put("title", title);
        for (List<String> list : lists) {
            Date date = MyDateUtil.format(list.get(4), "yyyyMMdd");
            if (MyDateUtil.dayOfWeek(date.getTime()) > 5) {
                continue;
            }
            if (list.get(5).equals(list.get(7))) {
                continue;
            }
            String key = list.get(0) + list.get(4) + list.get(5) + list.get(6) + list.get(7);
            Long start = MyDateUtil.format(list.get(4) + " " + list.get(5), "yyyyMMdd HHmm").getTime();
            Long end = MyDateUtil.format(list.get(6) + " " + list.get(7), "yyyyMMdd HHmm").getTime();
            Long s = MyDateUtil.format(list.get(4) + " " + "1130", "yyyyMMdd HHmm").getTime();
            Long e = MyDateUtil.format(list.get(6) + " " + "1300", "yyyyMMdd HHmm").getTime();
            int min = (int) ((end - start) / MyDateUtil.MINUTE);
            if (start <= s && end >= e) {
                min = min - 90;
            }
            list.set(8, min + "");
            map.put(key, list);
        }
        
        Map<String, List<String>> m = new LinkedHashMap<>();
        for (List<String> list:map.values()){
            String key = list.get(0) + list.get(4);
            if (m.get(key) == null) {
                m.put(key, list);
            } else {
                m.get(key).set(8, (Integer.parseInt(m.get(key).get(8)) + Integer.parseInt(list.get(8))) + "");
            }
        }
        List<List<String>> out = new ArrayList<>(m.values());
    
        MyExcelUtil.export(out, new File("/Users/suzui/Downloads/tmp/TSR_Stkstat_new.xlsx"));
        
    }
}
