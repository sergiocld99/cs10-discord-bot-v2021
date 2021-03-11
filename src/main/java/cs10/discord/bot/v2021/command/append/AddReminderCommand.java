package cs10.discord.bot.v2021.command.append;

import cs10.discord.bot.v2021.v1.common.Emoji;
import cs10.discord.bot.v2021.event.reminder.Reminder;
import cs10.discord.bot.v2021.io.UserPreferences;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Calendar;

public class AddReminderCommand implements AppendCommand {

    @Override
    public String getId() {
        return "reminder";
    }

    @Override
    public void execute(UserPreferences preferences, GuildMessageReceivedEvent event) {
        String entryText = event.getMessage().getContentDisplay()
                .replace(AppendCommand.PREFIX + getId(), "").trim();

        String[] params = entryText.split(" ");

        try {
            String dd = params[0].split("/")[0];
            String MM = params[0].split("/")[1];
            String hh = params[1].split(":")[0];
            String mm = params[1].split(":")[1];
            String message = params[2];
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dd));
            calendar.set(Calendar.MONTH, Integer.parseInt(MM)-1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hh));
            calendar.set(Calendar.MINUTE, Integer.parseInt(mm));
            Reminder reminder = new Reminder(calendar.getTimeInMillis(), message);
            preferences.addReminder(reminder);
            event.getChannel().sendMessage(Emoji.BELL.getCodename() + " You have " +
                    preferences.getReminders().size() + " reminders in this server now").queue();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
            errorMsg(event.getChannel());
        }
    }

    private void errorMsg(TextChannel channel){
        channel.sendMessage(Emoji.EXCUSE_ME.getCodename() +
                " Required parameters: [dd/MM] [hh:mm] [message]").queue();
    }
}
