package cs10.discord.bot.v2021.command.call;

import cs10.discord.bot.v2021.command.BotCommand;
import net.dv8tion.jda.api.entities.TextChannel;

public interface CallCommand extends BotCommand {
    void execute(TextChannel channel);
}
