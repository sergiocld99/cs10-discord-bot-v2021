package cs10.discord.bot.v2021.currency.bitcoin;

import cs10.discord.bot.v2021.currency.CurrencyItem;

import java.io.Serializable;

public class BitcoinItem extends CurrencyItem implements Serializable {

    public BitcoinItem(){}

    public BitcoinItem(double valueInUSD){
        this(valueInUSD, System.currentTimeMillis());
    }

    public BitcoinItem(double valueInUSD, long date) {
        super(valueInUSD, date);
    }
}
