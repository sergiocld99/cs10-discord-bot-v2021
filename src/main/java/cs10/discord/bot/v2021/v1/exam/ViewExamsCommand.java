package cs10.discord.bot.v2021.v1.exam;

import cs10.discord.bot.v2021.command.call.CallCommand;
import cs10.discord.bot.v2021.event.Event;
import cs10.discord.bot.v2021.io.UserPreferences;
import cs10.discord.bot.v2021.v1.common.Emoji;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Comparator;
import java.util.List;

public class ViewExamsCommand implements CallCommand {

    @Override
    public String getId() {
        return "exams";
    }

    @Override
    public void execute(UserPreferences preferences, TextChannel channel) {
        List<Exam> exams = preferences.getExams();
        removeExpired(exams);
        exams.sort(Comparator.comparingLong(Event::getTimestamp));

        if (exams.isEmpty()){
            String message = Emoji.MISUNDERSTAND.getCodename() + " - No hay exámenes guardados";
            channel.sendMessage(message).queue();
        } else {
            StringBuilder sb = new StringBuilder();
            for (Exam exam : exams) sb.append(exam).append('\n');
            channel.sendMessage(sb.toString()).queue();
        }
    }

    private void removeExpired(List<Exam> exams){
        exams.removeIf(e -> e.getTimestamp() < System.currentTimeMillis());
    }
}
