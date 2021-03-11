package cs10.discord.bot.v2021.v1.time;

import org.jetbrains.annotations.Nullable;

public enum Day {
    MONDAY("lunes"), TUESDAY("martes"), WEDNESDAY("miercoles"),
    THURSDAY("jueves"), FRIDAY("viernes"), SATURDAY("sabado"),
    SUNDAY("domingo");

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
}
