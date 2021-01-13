package cs10.discord.bot.v2021.currency.usd;

import cs10.discord.bot.v2021.currency.CurrencyItem;
import cs10.discord.bot.v2021.currency.CurrencyUpdater;
import cs10.discord.bot.v2021.model.GuildStatus;

public class BlueUpdater extends CurrencyUpdater {
    private static final int DEFAULT_RATE = 4;

    public BlueUpdater(GuildStatus guildStatus) {
        this(guildStatus, DEFAULT_RATE);
    }

    public BlueUpdater(GuildStatus guildStatus, int hourRate) {
        super(guildStatus, hourRate, new Dollar());
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
        return getPreferences().getDollarItem();
    }

    @Override
    protected void saveCurrent(double current) {
        getPreferences().setDollarItem(new DollarItem(current));
    }


}
