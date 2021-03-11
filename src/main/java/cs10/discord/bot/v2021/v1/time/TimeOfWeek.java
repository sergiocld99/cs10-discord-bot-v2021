package cs10.discord.bot.v2021.v1.time;

import java.io.Serializable;

public class TimeOfWeek implements Serializable {
    private int dayOfWeek;
    private TimeOfDay timeOfDay;

    public TimeOfWeek(Day day, TimeOfDay timeOfDay) {
        this.dayOfWeek = day.ordinal();
        this.timeOfDay = timeOfDay;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}
