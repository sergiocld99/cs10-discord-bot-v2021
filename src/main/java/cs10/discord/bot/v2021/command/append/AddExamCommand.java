package cs10.discord.bot.v2021.command.append;

import cs10.discord.bot.v2021.io.UserPreferences;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AddExamCommand implements AppendCommand {

    @Override
    public String getId() {
        return "exam";
    }

    @Override
    public void execute(UserPreferences preferences, GuildMessageReceivedEvent event) {

    }
}
