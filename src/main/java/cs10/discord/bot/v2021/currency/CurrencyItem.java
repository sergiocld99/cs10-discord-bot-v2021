package cs10.discord.bot.v2021.currency;

import cs10.discord.bot.v2021.common.Emoji;

import java.io.IOException;
import java.io.Serializable;

public abstract class CurrencyItem implements Serializable {
    private final double value;
    private final long time;

    public CurrencyItem(){
        value = 0;
        time = System.currentTimeMillis();
    }

    public CurrencyItem(double value, long time) {
        this.value = value;
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public long getTime() {
        return time;
    }
}
