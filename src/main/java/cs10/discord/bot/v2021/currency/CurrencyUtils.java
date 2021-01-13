package cs10.discord.bot.v2021.currency;

import java.text.DecimalFormat;

public class CurrencyUtils {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int variationFilter;

    public CurrencyUtils(int variationFilter) {
        this.variationFilter = variationFilter;
    }

    public void setVariationFilter(int variationFilter) {
        this.variationFilter = variationFilter;
    }

    public int getVariationFilter() {
        return variationFilter;
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
        return Math.abs(percentage) > variationFilter;
    }

    public static String twoDecimals(double number){
        return df.format(number);
    }
}
