package cs10.discord.bot.v2021.command.call;

import cs10.discord.bot.v2021.io.UserPreferences;
import cs10.discord.bot.v2021.v1.common.Emoji;
import net.dv8tion.jda.api.entities.TextChannel;

public class KillCommand implements CallCommand {

    @Override
    public String getId() {
        return "kill";
    }

    @Override
    public void execute(UserPreferences preferences, TextChannel channel) {
        channel.sendMessage("Adiós mundo cruel!... " +
                Emoji.DIED.getCodename()).queue(message -> System.exit(0));
    }
}
