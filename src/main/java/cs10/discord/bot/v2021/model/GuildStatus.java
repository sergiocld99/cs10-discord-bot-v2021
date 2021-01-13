package cs10.discord.bot.v2021.model;

import cs10.discord.bot.v2021.currency.bitcoin.BitcoinUpdater;
import cs10.discord.bot.v2021.currency.usd.BlueUpdater;
import cs10.discord.bot.v2021.io.UserPreferences;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

public class GuildStatus {
    private final Guild guild;
    private final UserPreferences preferences;

    // Quick access
    private TextChannel preferredChannel;

    public GuildStatus(Guild guild, UserPreferences preferences) {
        this.guild = guild;
        this.preferences = preferences;
        if (preferences.getPreferredChannelId() != null) findPreferredChannel();
        scheduleUpdaters();
    }

    public void findPreferredChannel(){
        for (TextChannel c : guild.getTextChannels()){
            if (c.getId().equals(preferences.getPreferredChannelId())){
                preferredChannel = c;
                return;
            }
        }

        System.err.println("Preferred Channel not found");
    }

    private void scheduleUpdaters(){
        new BitcoinUpdater(this);
        new BlueUpdater(this);
    }

    public UserPreferences getPreferences() {
        return preferences;
    }

    public Guild getGuild() {
        return guild;
    }

    public TextChannel getPreferredChannel() {
        return preferredChannel;
    }
}
