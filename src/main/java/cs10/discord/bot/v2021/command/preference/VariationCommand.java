package cs10.discord.bot.v2021.command.preference;

import cs10.discord.bot.v2021.io.UserPreferences;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class VariationCommand implements PreferenceCommand {

    @Override
    public void execute(UserPreferences preferences, GuildMessageReceivedEvent event) {
        String[] params = event.getMessage().getContentDisplay().split(" ");
        String message;

        if (params.length == 1) message = "Missing target variation value";
        else try {
            int value = Integer.parseInt(params[1]);
            if (value <= 0) message = "Parameter must be a positive number";
            else {
                preferences.setVariationFilter(value);
                message = "Target variation updated successfully";
            }
        } catch (NumberFormatException e){
            message = "Parameter must be an integer";
        }

        event.getChannel().sendMessage(message).queue();
    }

    @Override
    public String getId() {
        return "variation";
    }
}
