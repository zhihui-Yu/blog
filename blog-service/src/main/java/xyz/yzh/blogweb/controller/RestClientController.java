package xyz.yzh.blogweb.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author simple
 */
@RestController
public class RestClientController {
    private final String[] reviewer = {"ebin", "alex", "tkank", "simple"};
    private final Map<String, String> dateReviewer = new TreeMap<>();
    private final Map<String, Boolean> dateHolidays = new TreeMap<>();

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/date/reviewer")
    public String getReviewer() {
        var now = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        var res = restTemplate.getForEntity("https://timor.tech/api/holiday/year/" + now.getYear(), Res.class).getBody();
        putValues(res.holiday, now);
        return JSON.toJSONString(dateHolidays);
    }

    private void putValues(Map<String, Res.Holiday> holidayMap, ZonedDateTime now) {
        // 12个月， 每个月的天数， 每天的人
        int reviewerIndex = 0;
        int year = now.getYear();
        for (int month = 1; month <= 12; month++) {
            for (int day = 1; day <= getDayOfMonth(now.getYear(), month); day++) { // skip 01-01
                var formatDay = formatNum(month) + "-" + formatNum(day);
                var key = year + "-" + formatDay;
                if (day == 1 && month == 1) {
                    dateHolidays.put(key, true);
                    dateReviewer.put(key, reviewer[reviewerIndex]);
                } else {
                    dateHolidays.put(key, isRestTime(holidayMap.get(formatDay), year, month, day));
                    reviewerIndex = calcReviewerIndex(reviewerIndex, year, month, day);
                    dateReviewer.put(key, reviewer[reviewerIndex]);
                }
            }
        }
    }

    private boolean isRestTime(Res.Holiday holiday, int year, int month, int day) {
        if (holiday != null) return holiday.holiday; // true -> holiday, false -> Supplementary class
        return isWeekend(year, month, day);
    }

    private boolean isWeekend(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        return i == 1 || i == 7;
    }

    private int calcReviewerIndex(int reviewerIndex, int year, int month, int day) {
        String previousDay = previousDay(year, month, day);
        if (dateHolidays.get(previousDay)) return reviewerIndex;
        return (reviewerIndex + 1) % 4;
    }

    private String previousDay(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day).minusDays(1);
        return localDate.getYear() + "-" + formatNum(localDate.getMonthValue()) + "-" + formatNum(localDate.getDayOfMonth());
    }

    private int getDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    private String formatNum(int i) {
        return i < 10 ? "0" + i : "" + i;
    }

    public static class Res {
        public Integer code;
        public Map<String, Holiday> holiday;

        public static class Holiday {
            public Boolean holiday = Boolean.FALSE;
            public String name;
            public String date;
        }
    }
}
