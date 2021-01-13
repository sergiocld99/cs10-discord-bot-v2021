package cs10.discord.bot.v2021.currency;

import cs10.discord.bot.v2021.common.Emoji;

import java.io.IOException;

public abstract class Currency {
    public abstract double getPriceAsDouble() throws IOException;
    public abstract String getName();
    public abstract String getUnit();
    public abstract Emoji getEmoji();

    public String getCurrentInfo(){
        try {
            double current = getPriceAsDouble();
            return getEmoji().getCodename() + " Current value: $" +
                    CurrencyUtils.twoDecimals(current) + " " + getUnit();
        } catch (IOException e){
            return "An error occurred: " + e.getMessage();
        }
    }
}
