package com.kk.thw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期处理工具类
 *
 * @author tianhuiwen
 * @date 2018-11-05
 */
public class DateUtil {

    /**
     * 获取当前的整点时间(小时)和（前或者后）n个小时的整点时间
     *
     * @return [2018-10-22 00:00,2018-10-22 06:00]
     * @throws ParseException 解析异常
     */
    public static List<Date> getEntireTimes(int n) throws ParseException {
        ArrayList<Date> dates = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        Date nowDate = new Date();
        Date endTime = formatter.parse(formatter.format(nowDate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.add(Calendar.HOUR_OF_DAY, n);
        dates.add(calendar.getTime());
        dates.add(endTime);
        return dates;
    }

    /**
     * 获取当前的整点时间(月)和（后）n个月的整点时间
     *
     * @return [2018-10-22 00:00,2018-10-22 06:00]
     * @throws ParseException 解析异常
     */
    public static List<Date> getMonthEntireTimes(int n) throws ParseException {
        ArrayList<Date> dates = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String format = formatter.format(new Date());
        format = format + "-01 00:00:00";
        SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = t.parse(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        calendar.add(Calendar.MONTH, 1);
        Date endTime = calendar.getTime();
        calendar.add(Calendar.MONTH, n);
        Date startTime = calendar.getTime();
        dates.add(startTime);
        dates.add(endTime);
        return dates;
    }
}
