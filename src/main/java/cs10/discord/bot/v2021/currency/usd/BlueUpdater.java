package cs10.discord.bot.v2021.currency.usd;

import cs10.discord.bot.v2021.currency.CurrencyItem;
import cs10.discord.bot.v2021.currency.CurrencyUpdater;
import cs10.discord.bot.v2021.v1.guild.GuildStatus;

public class BlueUpdater extends CurrencyUpdater {
    private static final int DEFAULT_RATE = 100;

    public BlueUpdater(GuildStatus guildStatus) {
        this(guildStatus, DEFAULT_RATE);
    }

    private BlueUpdater(GuildStatus guildStatus, int minutesRate) {
        super(guildStatus, minutesRate, new Dollar());
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
