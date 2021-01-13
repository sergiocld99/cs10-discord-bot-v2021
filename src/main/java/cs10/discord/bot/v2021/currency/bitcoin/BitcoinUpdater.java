package cs10.discord.bot.v2021.currency.bitcoin;

import cs10.discord.bot.v2021.currency.CurrencyItem;
import cs10.discord.bot.v2021.currency.CurrencyUpdater;
import cs10.discord.bot.v2021.model.GuildStatus;

public class BitcoinUpdater extends CurrencyUpdater {
    private static final int DEFAULT_RATE = 1;

    public BitcoinUpdater(GuildStatus guildStatus) {
        this(guildStatus, DEFAULT_RATE);
    }

    public BitcoinUpdater(GuildStatus guildStatus, int hourRate) {
        super(guildStatus, hourRate, new Bitcoin());
    }

    @Override
    protected void updateDecreasing(double variation, double current){
        sendDecreasingMessage(variation, current);
    }

    @Override
    protected void updateIncreasing(double variation, double current){
        sendIncreasingMessage(variation, current);
    }

    @Override
    protected CurrencyItem getPrevious() {
        return getPreferences().getBitcoinItem();
    }

    @Override
    protected void saveCurrent(double current) {
        getPreferences().setBitcoinItem(new BitcoinItem(current));
    }
}
