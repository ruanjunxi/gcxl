package com.example.gcxl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author:
 * @Description:
 */
public class helper {
    public String dateConversion(Date date1) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = simpleDateFormat.parse(dateStr);
        String time=simpleDateFormat.format(date1);
        return time;
    }
    public String dateStrConversion(String dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateStr);
        String time=simpleDateFormat.format(date);
        return time;
    }
}
