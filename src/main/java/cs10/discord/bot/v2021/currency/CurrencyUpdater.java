package cs10.discord.bot.v2021.currency;

import cs10.discord.bot.v2021.common.Emoji;
import cs10.discord.bot.v2021.io.UserPreferences;
import cs10.discord.bot.v2021.model.GuildStatus;
import net.dv8tion.jda.api.entities.TextChannel;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class CurrencyUpdater {
    private final GuildStatus guildStatus;
    private final CurrencyUtils currencyUtils;
    private final Currency currency;
    private int hourRate;

    public CurrencyUpdater(GuildStatus guildStatus, int hourRate, Currency currency) {
        this.currencyUtils = new CurrencyUtils(guildStatus.getPreferences().getVariationFilter());
        this.guildStatus = guildStatus;
        this.hourRate = hourRate;
        this.currency = currency;
        runFixedRate();
    }

    private void runFixedRate(){
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        s.scheduleAtFixedRate(this::runOnce, 0, hourRate, TimeUnit.HOURS);
    }

    protected abstract CurrencyItem getPrevious();
    protected abstract void updateDecreasing(double variation, double current);
    protected abstract void updateIncreasing(double variation, double current);
    protected abstract void saveCurrent(double current);

    private void runOnce() {
        try {
            System.out.println("Running " + currency.getName() + " updater");
            double current = currency.getPriceAsDouble();
            CurrencyItem prev = getPrevious();
            if (prev != null && prev.getValue() != 0){
                double variation = CurrencyUtils.calculateVariation(prev.getValue(), current);
                if (getCurrencyUtils().matters(variation)){
                    if (variation < 0) updateDecreasing(variation, current);
                    else if (variation > 0) updateIncreasing(variation, current);
                } else System.out.println(currency.getName() + " variation is low: " +
                        CurrencyUtils.twoDecimals(variation) + "%");
            } else saveCurrent(current);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void sendDecreasingMessage(double variation, double current){
        sendMessage(Emoji.DECREASING.getCodename() + " " + currency.getName() +
                " decreased " + CurrencyUtils.twoDecimals(-variation) + "% -- " +
                currency.getEmoji().getCodename() + " $" + CurrencyUtils.twoDecimals(current) +
                " " + currency.getUnit());

        saveCurrent(current);
    }

    protected void sendIncreasingMessage(double variation, double current){
        sendMessage(Emoji.INCREASING.getCodename() + " " + currency.getName() +
                " increased " + CurrencyUtils.twoDecimals(variation) + "% -- " +
                currency.getEmoji().getCodename() + " $" + CurrencyUtils.twoDecimals(current) +
                " " + currency.getUnit());

        saveCurrent(current);
    }

    protected void sendMessage(String string){
        TextChannel channel = guildStatus.getPreferredChannel();
        if (channel != null) channel.sendMessage(string).queue();
        else System.err.println("Preferred channel for " + guildStatus.getGuild().getName() +
                " not found");
    }

    public void setHourRate(int hourRate) {
        this.hourRate = hourRate;
    }

    public int getHourRate() {
        return hourRate;
    }

    public UserPreferences getPreferences() {
        return guildStatus.getPreferences();
    }

    public CurrencyUtils getCurrencyUtils() {
        return currencyUtils;
    }
}
