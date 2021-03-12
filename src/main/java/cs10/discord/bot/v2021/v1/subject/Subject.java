package cs10.discord.bot.v2021.v1.subject;

import cs10.discord.bot.v2021.v1.common.Emoji;
import cs10.discord.bot.v2021.v1.common.Simplifier;
import cs10.discord.bot.v2021.v1.time.Day;
import cs10.discord.bot.v2021.v1.time.TimeOfDay;
import cs10.discord.bot.v2021.v1.time.TimeOfWeek;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Subject {
    private final TextChannel channel;
    private final ArrayList<TimeOfWeek> scheduleTimes;
    private String link;

    private Subject(ArrayList<TimeOfWeek> scheduleTimes, TextChannel channel){
        this.scheduleTimes = scheduleTimes;
        this.channel = channel;
    }

    public static Subject create(@NotNull String topic, TextChannel channel){
        String[] words = topic.split(" ");

        // Validation
        if (words.length >= 2){
            ArrayList<TimeOfWeek> list = new ArrayList<>();
            int index = 0;

            while (index < words.length - 1){
                index = analyzeTopic(list, words, index);
            }

            if (list.size() > 0){
                Subject subject = new Subject(list, channel);
                String lastWord = words[words.length-1];
                if (isLink(lastWord)) subject.setLink(lastWord);
                return subject;
            }
        }

        return null;
    }

    private static int analyzeTopic(@NotNull List<TimeOfWeek> list, String[] words, int index){
        // First, we search for a day word
        Day day = null;

        while (day == null && index < words.length - 1){
            day = Day.get(Simplifier.with(words[index]));
            index++;
        }

        // Second, we read the schedule
        if (day != null){
            TimeOfDay timeOfDay = TimeOfDay.create(words[index]);
            list.add(new TimeOfWeek(day, timeOfDay));
        }

        return index;
    }

    private static boolean isLink(String s){
        return s.startsWith("https://");
    }

    public void startSchedulers(TimeOfWeek currentTimeOfWeek){
        for (TimeOfWeek tw : scheduleTimes){
            if (tw.getDayOfWeek() == currentTimeOfWeek.getDayOfWeek()){
                int delay = tw.getTimeOfDay().getMinutesTo(currentTimeOfWeek.getTimeOfDay());
                if (delay > 10) {
                    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                    service.schedule(new ClassTimeRunnable(10), delay-10, TimeUnit.MINUTES);
                }
            }
        }
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<TimeOfWeek> getScheduleTimes() {
        return scheduleTimes;
    }

    public TextChannel getChannel() {
        return channel;
    }


    public class ClassTimeRunnable implements Runnable {
        private final int nextAlarm;

        public ClassTimeRunnable(int nextAlarm) {
            this.nextAlarm = nextAlarm;
        }

        @Override
        public void run() {
            String message;

            if (nextAlarm == 0){
                message = Emoji.CALENDAR.getCodename() + " - The class is starting right now!";
            } else {
                message = Emoji.BELL.getCodename() + " - " + channel.getAsMention() +
                        " in " + nextAlarm + " minutes";
                if (link != null) message = message + "\n" + link;
                scheduleNext();
            }

            channel.sendMessage(message).queue();
        }

        private void scheduleNext(){
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.schedule(new ClassTimeRunnable(0), nextAlarm, TimeUnit.MINUTES);
        }
    }
}
