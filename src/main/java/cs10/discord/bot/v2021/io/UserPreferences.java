package cs10.discord.bot.v2021.io;

import cs10.discord.bot.v2021.currency.bitcoin.BitcoinItem;
import cs10.discord.bot.v2021.currency.usd.DollarItem;

import java.io.*;

public class UserPreferences implements Serializable {
    private BitcoinItem bitcoinItem;
    private DollarItem dollarItem;
    private String guildId, preferredChannelId;
    private int timesOpened, variationFilter = 10;
    private static final int VERSION = 1;

    public UserPreferences() { }

    public UserPreferences(String guildId) {
        this.guildId = guildId;
        save();
    }

    public static UserPreferences load(String guildId){
        try {
            File file = new File(guildId);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            return (UserPreferences) ois.readObject();
        } catch (IOException e){
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e){
            System.err.println("Incorrect saved object");
            e.printStackTrace();
        }

        return new UserPreferences(guildId);
    }

    public BitcoinItem getBitcoinItem() {
        return bitcoinItem;
    }

    public void setBitcoinItem(BitcoinItem bitcoinItem) {
        this.bitcoinItem = bitcoinItem;
        save();
    }

    public DollarItem getDollarItem() {
        return dollarItem;
    }

    public void setDollarItem(DollarItem dollarItem) {
        this.dollarItem = dollarItem;
        save();
    }

    public int getVariationFilter() {
        return variationFilter;
    }

    public void setVariationFilter(int variationFilter) {
        this.variationFilter = variationFilter;
        save();
    }

    public String getPreferredChannelId() {
        return preferredChannelId;
    }

    public void setPreferredChannelId(String preferredChannelId) {
        this.preferredChannelId = preferredChannelId;
        save();
    }

    public void save(){
        try {
            System.out.println("Saving preferences");
            IOUtils.save(this, guildId);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
