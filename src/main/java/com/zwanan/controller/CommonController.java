package com.zwanan.controller;

import com.zwanan.entity.CalendarBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@RequestMapping("/")
@Controller
public class CommonController {

    /**
     * 首页视图跳转
     * @return
     */
    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/calendar")
    public String calendar(Model model) {
        var calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        CalendarBean calendarBean = new CalendarBean(year, month, day);
        model.addAttribute("calendarBean", calendarBean);
        return "calendar/calendar";
    }

}
