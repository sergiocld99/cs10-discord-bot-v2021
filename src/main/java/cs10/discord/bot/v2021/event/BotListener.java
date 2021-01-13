package cs10.discord.bot.v2021.event;

import cs10.discord.bot.v2021.command.preference.PreferenceCommand;
import cs10.discord.bot.v2021.command.call.BitcoinCommand;
import cs10.discord.bot.v2021.command.call.CallCommand;
import cs10.discord.bot.v2021.command.call.DollarCommand;
import cs10.discord.bot.v2021.command.preference.ChannelCommand;
import cs10.discord.bot.v2021.command.preference.VariationCommand;
import cs10.discord.bot.v2021.model.GuildStatus;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class BotListener extends ListenerAdapter {
    private final Set<GuildStatus> guilds = new HashSet<>();
    private final Set<CallCommand> callCommands = new HashSet<>();
    private final Set<PreferenceCommand> preferenceCommands = new HashSet<>();

    public BotListener(){
        fillCommandsSet();
    }

    private void fillCommandsSet(){
        callCommands.add(new BitcoinCommand());
        callCommands.add(new DollarCommand());
        preferenceCommands.add(new ChannelCommand());
        preferenceCommands.add(new VariationCommand());
    }

    public void addGuildStatus(GuildStatus status){
        guilds.add(status);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        for (GuildStatus s : guilds){
            if (s.getGuild().getId().equals(event.getGuild().getId())) {
                String message = event.getMessage().getContentDisplay();
                System.out.println(message + " received from known guild");
                if (message.startsWith("$")) executeCallCommand(event);
                else if (message.startsWith("&")) executePreferenceCommand(event, s);
                return;
            }
        }

        System.err.println("Unknown guild");
    }

    private void executeCallCommand(GuildMessageReceivedEvent event){
        String command = event.getMessage().getContentDisplay().substring(1);

        for (CallCommand c : callCommands){
            if (c.getId().equals(command)){
                c.execute(event.getChannel());
                return;
            }
        }

        System.err.println(command + " not found");
    }

    private void executePreferenceCommand(GuildMessageReceivedEvent event, GuildStatus status){
        String command = event.getMessage().getContentDisplay().split(" ")[0].substring(1);

        for (PreferenceCommand c : preferenceCommands){
            if (c.getId().equals(command)){
                c.execute(status.getPreferences(), event);
                return;
            }
        }

        System.err.println(command + " not found");
    }
}
