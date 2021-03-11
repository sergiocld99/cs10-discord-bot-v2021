package cs10.discord.bot.v2021.v1.time;

import cs10.discord.bot.v2021.v1.common.Simplifier;
import org.jetbrains.annotations.Nullable;

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
            if (hour != -1 && minute != -1) return new TimeOfDay(hour, minute);
        }

        return null;
    }
}
