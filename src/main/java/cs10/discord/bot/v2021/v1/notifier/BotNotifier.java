package cs10.discord.bot.v2021.v1.notifier;

import cs10.discord.bot.v2021.v1.subject.Subject;
import cs10.discord.bot.v2021.v1.time.Day;
import cs10.discord.bot.v2021.v1.time.TimeOfDay;
import cs10.discord.bot.v2021.v1.time.TimeOfWeek;

import java.util.Calendar;
import java.util.List;

public class BotNotifier {

    public static void startClassTimeNotifier(List<Subject> subjects) {
        // current day of week
        Day currentDay = Day.get(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        TimeOfDay currentTimeOfDay = TimeOfDay.current();
        TimeOfWeek currentTimeOfWeek = new TimeOfWeek(currentDay, currentTimeOfDay);

        // analyze each
        for (Subject s : subjects) s.startSchedulers(currentTimeOfWeek);
    }
}
