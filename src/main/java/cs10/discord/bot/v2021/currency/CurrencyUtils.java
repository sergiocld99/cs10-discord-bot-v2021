package cs10.discord.bot.v2021.currency;

import cs10.discord.bot.v2021.io.UserPreferences;

import java.text.DecimalFormat;

public class CurrencyUtils {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final UserPreferences preferences;

    private int getVariationFilter(){
        return preferences.getVariationFilter();
    }

    public CurrencyUtils(UserPreferences preferences) {
        this.preferences = preferences;
    }

    /**
     * @param prev the saved value of the currency
     * @param current the new value of the currency
     * @return the variation as percentage, it can be positive or negative
     * @throws IllegalArgumentException if prev is 0
     */
    public static double calculateVariation(double prev, double current) throws IllegalArgumentException {
        if (prev == 0) throw new IllegalArgumentException("Previous value 0 not allowed here");
        double variation = current / prev - 1;
        return variation * 100;
    }

    public boolean matters(double percentage){
        if (percentage > 0) return percentage > (getVariationFilter() * 2);
        return Math.abs(percentage) > getVariationFilter();
    }

    public static String twoDecimals(double number){
        return df.format(number);
    }
}
