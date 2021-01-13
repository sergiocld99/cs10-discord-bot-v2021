package cs10.discord.bot.v2021.currency.usd;

import cs10.discord.bot.v2021.currency.CurrencyItem;

import java.io.Serializable;

public class DollarItem extends CurrencyItem implements Serializable {

    public DollarItem() { }

    public DollarItem(double valueInARS, long time) {
        super(valueInARS, time);
    }

    public DollarItem(double valueInARS){
        super(valueInARS, System.currentTimeMillis());
    }
}
