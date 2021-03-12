package cs10.discord.bot.v2021.v1.time;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

public enum Day {
    SUNDAY("domingo"),
    MONDAY("lunes"), TUESDAY("martes"), WEDNESDAY("miercoles"),
    THURSDAY("jueves"), FRIDAY("viernes"), SATURDAY("sabado"),;

    Day(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    private final String translation;

    @Nullable
    public static Day get(String simplifiedWord){
        // We try with english and translation words
        for (Day day : Day.values()){
            if (day.name().toLowerCase().equals(simplifiedWord)) return day;
            if (day.translation.equals(simplifiedWord)) return day;
        }

        return null;
    }

    public static Day get(int dayOfWeek){
        switch (dayOfWeek){
            case Calendar.SUNDAY: return SUNDAY;
            case Calendar.MONDAY: return MONDAY;
            case Calendar.TUESDAY: return TUESDAY;
            case Calendar.WEDNESDAY: return WEDNESDAY;
            case Calendar.THURSDAY: return THURSDAY;
            case Calendar.FRIDAY: return FRIDAY;
            default: return SATURDAY;
        }
    }
}
