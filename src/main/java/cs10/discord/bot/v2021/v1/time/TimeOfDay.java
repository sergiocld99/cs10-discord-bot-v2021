package cs10.discord.bot.v2021.v1.time;

import cs10.discord.bot.v2021.v1.common.Simplifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

public class TimeOfDay {
    private int hour, minute;

    private TimeOfDay(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Nullable
    public static TimeOfDay create(String s){
        String[] params = s.split(":");
        if (params.length == 2){
            int hour = Simplifier.toPositiveNumber(params[0]);
            int minute = Simplifier.toPositiveNumber(params[1]);
            if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60)
                return new TimeOfDay(hour, minute);
        }

        return null;
    }

    @NotNull
    public static TimeOfDay current(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new TimeOfDay(hour, minute);
    }

    public int getMinutesTo(TimeOfDay other){
        return getMinutesOfDay() - other.getMinutesOfDay();
    }

    public int getMinutesOfDay(){
        return hour * 60 + minute;
    }
}
