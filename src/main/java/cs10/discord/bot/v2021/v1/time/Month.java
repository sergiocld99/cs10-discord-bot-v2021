package cs10.discord.bot.v2021.v1.time;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public enum Month {
    JANUARY("enero",31), FEBRUARY("febrero",28),
    MARCH("marzo",30), APRIL("abril",30),
    MAY("mayo",31), JUNE("junio",30),
    JULY("julio",31), AUGUST("agosto",31),
    SEPTEMBER("septiembre",30), OCTOBER("octubre",31),
    NOVEMBER("noviembre",30), DECEMBER("diciembre",31);

    private final String translation;
    private final int days;

    Month(String translation, int days) {
        this.translation = translation;
        this.days = days;
    }

    public String getTranslation() {
        return translation;
    }

    public int getDays() {
        return days;
    }

    @NotNull
    public static String getRandomDay(){
        Month r = getRandomMonth();
        int day = new Random().nextInt(r.days) + 1;
        return day + " de " + r.translation;
    }

    @NotNull
    public static Month getRandomMonth(){
        Month[] values = Month.values();
        return values[new Random().nextInt(values.length)];
    }

    @Nullable
    public static Month get(String simplifiedName){
        for (Month m : Month.values()){
            if (m.translation.equals(simplifiedName)){
                return m;
            }
        }

        return null;
    }
}
