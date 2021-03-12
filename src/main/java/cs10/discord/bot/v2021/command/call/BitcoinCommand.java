package cs10.discord.bot.v2021.command.call;

import cs10.discord.bot.v2021.currency.bitcoin.Bitcoin;
import cs10.discord.bot.v2021.io.UserPreferences;
import net.dv8tion.jda.api.entities.TextChannel;

public class BitcoinCommand implements CallCommand {
    private final Bitcoin bitcoin = new Bitcoin();

    @Override
    public void execute(UserPreferences preferences, TextChannel channel){
        channel.sendMessage(bitcoin.getCurrentInfo()).queue();
    }

    @Override
    public String getId() {
        return "btc";
    }
}
