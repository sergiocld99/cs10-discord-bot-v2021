package cs10.discord.bot.v2021.v1.listener;

import cs10.discord.bot.v2021.command.append.AddReminderCommand;
import cs10.discord.bot.v2021.command.append.AppendCommand;
import cs10.discord.bot.v2021.command.call.BitcoinCommand;
import cs10.discord.bot.v2021.command.call.CallCommand;
import cs10.discord.bot.v2021.command.call.DollarCommand;
import cs10.discord.bot.v2021.command.call.KillCommand;
import cs10.discord.bot.v2021.command.preference.ChannelCommand;
import cs10.discord.bot.v2021.command.preference.PreferenceCommand;
import cs10.discord.bot.v2021.command.preference.VariationCommand;
import cs10.discord.bot.v2021.v1.common.Emoji;
import cs10.discord.bot.v2021.v1.exam.AddExamCommand;
import cs10.discord.bot.v2021.v1.exam.ViewExamsCommand;
import cs10.discord.bot.v2021.v1.guild.GuildStatus;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class BotListener extends ListenerAdapter {
    private final Set<GuildStatus> guilds = new HashSet<>();
    private final Set<CallCommand> callCommands = new HashSet<>();
    private final Set<PreferenceCommand> preferenceCommands = new HashSet<>();
    private final Set<AppendCommand> appendCommands = new HashSet<>();

    // R71 Update
    private ListenerContext context;
    private AddExamCommand addExamCommand;

    public BotListener(){
        fillCommandsSet();
    }

    private void fillCommandsSet(){
        callCommands.add(new BitcoinCommand());
        callCommands.add(new DollarCommand());
        callCommands.add(new KillCommand());
        callCommands.add(new ViewExamsCommand());
        preferenceCommands.add(new ChannelCommand());
        preferenceCommands.add(new VariationCommand());
        appendCommands.add(new AddReminderCommand());

        addExamCommand = new AddExamCommand();
        appendCommands.add(addExamCommand);
    }

    public void addGuildStatus(GuildStatus status){
        guilds.add(status);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentDisplay();

        for (GuildStatus s : guilds){
            if (s.getGuild().getId().equals(event.getGuild().getId())) {
                if (context != null) switch (context){
                    case WAITING_EXAM_DATE:
                        context = addExamCommand.postDate(event);
                        break;
                    case WAITING_EXAM_HOUR:
                        context = addExamCommand.postHour(s.getPreferences(), event);
                        break;
                } else {
                    if (message.startsWith(CallCommand.PREFIX)) executeCallCommand(event, s);
                    else if (message.startsWith(PreferenceCommand.PREFIX)) executePreferenceCommand(event, s);
                    else if (message.startsWith(AppendCommand.PREFIX)) executeAppendCommand(event, s);
                }

                if (message.contains("F")) event.getChannel().sendMessage(Emoji.F.getCodename()).queue();
                return;
            }
        }
        System.err.println("Unknown guild");
    }

    private void executeCallCommand(GuildMessageReceivedEvent event, GuildStatus status){
        String command = event.getMessage().getContentDisplay().substring(1);

        for (CallCommand c : callCommands){
            if (c.getId().equals(command)){
                c.execute(status.getPreferences(), event.getChannel());
                return;
            }
        }

        System.err.println(command + " not found");
    }

    private void executePreferenceCommand(GuildMessageReceivedEvent event, GuildStatus status){
        String command = event.getMessage().getContentDisplay().split(" ")[0].substring(1);

        for (PreferenceCommand c : preferenceCommands){
            if (c.getId().equals(command)){
                context = c.execute(status.getPreferences(), event);
                return;
            }
        }

        System.err.println(command + " not found");
    }

    private void executeAppendCommand(GuildMessageReceivedEvent event, GuildStatus status){
        String command = event.getMessage().getContentDisplay().split(" ")[0].substring(1);

        for (AppendCommand c : appendCommands){
            if (c.getId().equals(command)){
                context = c.execute(status.getPreferences(), event);
                return;
            }
        }

        System.err.println(command + " not found");
    }
}
