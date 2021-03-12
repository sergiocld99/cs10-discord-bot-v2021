package cs10.discord.bot.v2021.v1.exam;

import cs10.discord.bot.v2021.command.append.AppendCommand;
import cs10.discord.bot.v2021.event.DateUtils;
import cs10.discord.bot.v2021.io.UserPreferences;
import cs10.discord.bot.v2021.v1.common.Emoji;
import cs10.discord.bot.v2021.v1.common.Simplifier;
import cs10.discord.bot.v2021.v1.exception.IllegalMonthException;
import cs10.discord.bot.v2021.v1.listener.ListenerContext;
import cs10.discord.bot.v2021.v1.time.Month;
import cs10.discord.bot.v2021.v1.time.TimeOfDay;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Calendar;

public class AddExamCommand implements AppendCommand {
    private String name;
    private final Calendar calendar = Calendar.getInstance();

    @Override
    public String getId() {
        return "exam";
    }

    @Override
    public ListenerContext execute(UserPreferences preferences, GuildMessageReceivedEvent event) {
        String entry = event.getMessage().getContentRaw()
                .replace(AppendCommand.PREFIX+getId(), "")
                .trim();

        if (entry.length() > 6){
            name = entry;
            String message = Emoji.GRINNING.getCodename() + " - Nice, ahora ingresá la fecha en este estilo: " + Month.getRandomDay();
            sendMessage(message, event.getChannel());
            return ListenerContext.WAITING_EXAM_DATE;
        } else if (entry.equals("matera")){
            preferences.clearExams();
            String message = Emoji.SUNGLASSES.getCodename() + " - Todos tus exámenes fueron borrados";
            sendMessage(message, event.getChannel());
            return null;
        }

        String message = Emoji.EXCUSE_ME.getCodename() + " - El nombre del examen es demasiado corto";
        sendMessage(message, event.getChannel());
        return null;
    }

    public ListenerContext postDate(GuildMessageReceivedEvent event){
        String[] words = event.getMessage().getContentRaw().trim().split(" ");

        if (words.length == 3) try {
            // First, search for month
            Month month = Month.get(Simplifier.with(words[2]));
            if (month == null) throw new IllegalMonthException(words[2]);

            // Second, validate date
            int day = Integer.parseInt(words[0]);
            if (day > 0 && day < month.getDays()){
                calendar.set(Calendar.MONTH, month.ordinal());
                calendar.set(Calendar.DAY_OF_MONTH, day);
                if (calendar.before(Calendar.getInstance()))
                    calendar.add(Calendar.YEAR, 1);

                String message = Emoji.GRINNING.getCodename() + " - Muy bien! Ahora solo falta la hora en el formato HH:mm";
                sendMessage(message, event.getChannel());
                return ListenerContext.WAITING_EXAM_HOUR;
            }

            String message = Emoji.CALENDAR.getCodename() + Emoji.FAIL.getCodename() +
                    " - No existe el día " + event.getMessage().getContentRaw();
            sendMessage(message, event.getChannel());

        } catch (NumberFormatException e){
            String message = Emoji.EXCUSE_ME.getCodename() + " - " + words[0] + " no es un número";
            sendMessage(message, event.getChannel());
        } catch (Exception e){
            sendMessage(e.getMessage(), event.getChannel());
        } else {
            String message = Emoji.EXCUSE_ME.getCodename() + " - Tenés que ingresar una fecha en el formato adecuado";
            sendMessage(message, event.getChannel());
        }

        return ListenerContext.WAITING_EXAM_DATE;
    }

    public ListenerContext postHour(UserPreferences preferences, GuildMessageReceivedEvent event){
        TimeOfDay timeOfDay = TimeOfDay.create(event.getMessage().getContentRaw().trim());
        if (timeOfDay != null){
            calendar.set(Calendar.HOUR_OF_DAY, timeOfDay.getHour());
            calendar.set(Calendar.MINUTE, timeOfDay.getMinute());
            String when = DateUtils.format(calendar.getTime());

            // Save to local device
            Exam exam = new Exam(calendar.getTimeInMillis(), event.getChannel().getId(), name);
            preferences.addExam(exam);

            String message = Emoji.CHECK.getCodename() + " - Perfecto! Se guardó el examen " + name + ", que será el día " + when;
            sendMessage(message, event.getChannel());

            // Restore variables
            name = null;
            calendar.setTimeInMillis(System.currentTimeMillis());

            return null;
        }

        String message = Emoji.CLOCK.getCodename() + Emoji.FAIL.getCodename() + " - No ingresaste una hora válida";
        sendMessage(message, event.getChannel());
        return ListenerContext.WAITING_EXAM_HOUR;
    }

    private void sendMessage(String message, TextChannel channel){
        channel.sendMessage(message).queue();
    }
}
