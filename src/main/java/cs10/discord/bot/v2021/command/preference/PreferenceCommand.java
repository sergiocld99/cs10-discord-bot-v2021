package cs10.discord.bot.v2021.command.preference;

import cs10.discord.bot.v2021.command.BotCommand;
import cs10.discord.bot.v2021.io.UserPreferences;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface PreferenceCommand extends BotCommand {
    void execute(UserPreferences preferences, GuildMessageReceivedEvent event);
}
