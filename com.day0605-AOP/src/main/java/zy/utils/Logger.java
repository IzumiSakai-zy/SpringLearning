package zy.utils;

import java.util.Date;

public class Logger {
    private Date date;

    public void log(){
        System.out.println("日志："+date.toString());
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
