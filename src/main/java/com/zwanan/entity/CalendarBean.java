package com.zwanan.entity;

import lombok.Data;

@Data
public class CalendarBean {
    private String calendar = null;
    private Integer year = -1;
    private Integer month = -1;
    private Integer day = -1;

    public CalendarBean(int year, int month, int day) {
        this.year = year;this.month = month;this.day = day;
    }


    public String getCalendar() {
        StringBuilder builder = new StringBuilder();
        var rili = java.util.Calendar.getInstance();
        rili.set(year, month - 1, 1);   //将日历翻到year年month月1日,注意0表示一月,
        //依次类推,11表示12月。
        //获取1日是星期几(get方法返回的值是1表示星期日,返回的值是7表示星期六):
        int week = rili.get(java.util.Calendar.DAY_OF_WEEK) - 1;
        int days = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            days = 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        }
        if (month == 2) {
            if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                days = 29;
            } else {
                days = 28;
            }
        }
        String a[] = new String[42];
        for (int i = 0; i < week; i++) {
            a[i] = "**";
        }
        for (int i = week, n = 1; i < week + days; i++) {
            a[i] = String.valueOf(n);
            n++;
        }
        for (int i = week + days, n = 1; i < 42; i++) {
            a[i] = "**";
        }
        //用表格显示数组:
        builder.append("<table border=1 width=\"970\"  cellpadding=\"5\" bordercolor=\"green\"  Style=\"border-collapse:collapse\"");
        builder.append("<tr  bgcolor=\"#EFEFEF\">");
        String weekday[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        for (int k = 0; k < 7; k++) {
            builder.append("<td>" + weekday[k] + "</td>");
        }
        builder.append("</tr>");
        for (int k = 0; k < 42; k = k + 7) {
            builder.append("<tr>");
            for (int j = k; j < Math.min(7 + k, 42); j++) {
                if (a[j].equals(String.valueOf(day))) {
                    builder.append("<td bgcolor=\"yellow\">" + a[j] + "</td>");
                } else {
                    builder.append("<td>" + a[j] + "</td>");
                }
            }
            builder.append("</tr>");
        }
        builder.append("</table>");
        calendar = new String(builder);
        return calendar;
    }
}
