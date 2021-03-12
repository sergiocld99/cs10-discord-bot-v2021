package cs10.discord.bot.v2021.event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy");

    public static String getTimeOfDay(long timestamp){
        Date date = new Date(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int minute = calendar.get(Calendar.MINUTE);
        if (minute == 0) return onlyHour(calendar);
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + twoDigits(minute) + " hs";
    }

    private static String onlyHour(Calendar calendar){
        return calendar.get(Calendar.HOUR_OF_DAY) + " hs";
    }

    private static String twoDigits(int number){
        if (number < 10) return "0" + number;
        return String.valueOf(number);
    }

    public static String format(Date date){
        return formatter.format(date);
    }
}
