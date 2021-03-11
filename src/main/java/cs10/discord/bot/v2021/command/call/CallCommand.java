package cs10.discord.bot.v2021.command.call;

import cs10.discord.bot.v2021.command.BotCommand;
import net.dv8tion.jda.api.entities.TextChannel;

/**
 * Comandos que mandan una respuesta luego de ser invocados, pero no cambian la configuración
 */
public interface CallCommand extends BotCommand {
    String PREFIX = "$";

    void execute(TextChannel channel);
}
