package cs10.discord.bot.v2021.command.append;

import cs10.discord.bot.v2021.io.UserPreferences;
import cs10.discord.bot.v2021.v1.common.Emoji;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AddSubjectCommand implements AppendCommand {

    @Override
    public String getId() {
        return "subject";
    }

    @Override
    public void execute(UserPreferences preferences, GuildMessageReceivedEvent event) {
        String[] params = event.getMessage().getContentDisplay().split(" ");
        if (params.length < 2) event.getChannel().sendMessage("Missing channel reference").queue();
        else {
            //Subject subject = new Subject(params[1]);
            //preferences.addSubject(subject);
            event.getChannel().sendMessage(Emoji.BOOKS.getCodename() + " You have " +
                    preferences.getSubjects().size() + " subjects in this server now").queue();
        }
    }
}
