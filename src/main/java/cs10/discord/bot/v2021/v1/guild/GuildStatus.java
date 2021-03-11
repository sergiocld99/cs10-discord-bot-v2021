package cs10.discord.bot.v2021.v1.guild;

import cs10.discord.bot.v2021.currency.bitcoin.BitcoinUpdater;
import cs10.discord.bot.v2021.currency.usd.BlueUpdater;
import cs10.discord.bot.v2021.io.UserPreferences;
import cs10.discord.bot.v2021.v1.subject.Subject;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.LinkedList;

public class GuildStatus {
    private final Guild guild;
    private final UserPreferences preferences;

    // Quick access
    private TextChannel preferredChannel;

    // R71 Update
    private final LinkedList<Subject> subjects = new LinkedList<>();

    public GuildStatus(Guild guild, UserPreferences preferences) {
        this.guild = guild;
        this.preferences = preferences;
        if (preferences.getPreferredChannelId() != null) findPreferredChannel();
        scheduleUpdaters();

        // R71 Update
        readChannels(guild);
        System.out.println(subjects.size() + " subjects for " + guild.getName());
    }

    private void readChannels(Guild guild){
        for (TextChannel tc : guild.getTextChannels()){
            String topic = tc.getTopic();
            if (topic != null){
                Subject subject = Subject.create(topic, tc);
                if (subject != null) subjects.add(subject);
            }
        }
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
