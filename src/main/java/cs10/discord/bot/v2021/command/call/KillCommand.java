package cs10.discord.bot.v2021.command.call;

import net.dv8tion.jda.api.entities.TextChannel;

public class KillCommand implements CallCommand {

    @Override
    public String getId() {
        return "kill";
    }

    @Override
    public void execute(TextChannel channel) {
        channel.sendMessage("Adiós mundo cruel!... x_x").queue(message -> System.exit(0));
    }
}
