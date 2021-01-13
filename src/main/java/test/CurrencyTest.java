package test;

import cs10.discord.bot.v2021.currency.bitcoin.BitcoinUpdater;
import cs10.discord.bot.v2021.currency.usd.BlueUpdater;
import cs10.discord.bot.v2021.io.UserPreferences;

public class CurrencyTest {

    public static void main(String[] args) {
        UserPreferences preferences = UserPreferences.load("options.dat");
        preferences.setVariationFilter(10);

        //new BitcoinUpdater(preferences);
        //new BlueUpdater(preferences);
    }
}
