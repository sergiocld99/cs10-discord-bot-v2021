package cs10.discord.bot.v2021.command.preference;

import cs10.discord.bot.v2021.io.UserPreferences;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ChannelCommand implements PreferenceCommand {

    @Override
    public void execute(UserPreferences preferences, GuildMessageReceivedEvent event) {
        TextChannel channel = event.getChannel();

        if (channel.canTalk()){
            preferences.setPreferredChannelId(channel.getId());
            channel.sendMessage("Preferred Channel updated successfully").queue();
        } else System.err.println("Bot can't talk in " + channel.getName());
    }

    @Override
    public String getId() {
        return "channel";
    }
}
