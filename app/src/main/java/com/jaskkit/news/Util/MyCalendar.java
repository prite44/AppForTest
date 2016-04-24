package com.jaskkit.news.Util;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by jaskkit on 04/24/2016.
 */
public class MyCalendar {

    private static MyCalendar instance;

    public static MyCalendar getInstance() {
        if (instance == null)
            instance = new MyCalendar();
        return instance;
    }
    private Calendar calendar;
    private SimpleDateFormat sdf;
    private Context mContext;
    private int year;
    private int month;
    private int dates;
    private int hourOfDay;
    private int minute;
    private int second;
    private String[] thaiLongMonth = {"มกราคม","กุมภาพันธ์","มีนาคม",
            "เมษายน","พฤษภาคม","มิถุนายน", "กรกฎาคม","สิงหาคม","กันยายน",
            "ตุลาคม","พฤศจิกายน","ธันวาคม"};
    private String[] EngLongMonth = {"January","February","March","April","May","June","July"
            ,"August","September","October","November","December"};

    private MyCalendar() {
        sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        calendar = new GregorianCalendar();
    }
    public  void SetCalendar(String s){
        year = Integer.valueOf(s.substring(0, 4));
        month = Integer.valueOf(s.substring(5,7));
        dates = Integer.valueOf(s.substring(8,10));
        hourOfDay = Integer.valueOf(s.substring(11,13));
        minute = Integer.valueOf(s.substring(14,16));
        second = Integer.valueOf(s.substring(17,19));
        this.calendar.set(year, month, dates, hourOfDay, minute, second);
    }
    public String getThaidate(String s){
        SetCalendar(s);
        Log.i("getThaidateLog",dates+" "+thaiLongMonth[month-1]+" "+year+" "+hourOfDay+":"+minute+":"+second);
        return dates+" "+thaiLongMonth[month-1]+" "+year+" "+hourOfDay+":"+minute+":"+second;
    }
    public String getEngdate(String s){
        SetCalendar(s);
        return dates+" "+EngLongMonth[month-1]+" "+year+" "+hourOfDay+":"+minute+":"+second;
    }
}
