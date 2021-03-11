package cs10.discord.bot.v2021.io;

import cs10.discord.bot.v2021.currency.bitcoin.BitcoinItem;
import cs10.discord.bot.v2021.currency.usd.DollarItem;
import cs10.discord.bot.v2021.event.exam.Exam;
import cs10.discord.bot.v2021.event.reminder.Reminder;
import cs10.discord.bot.v2021.v1.subject.Subject;

import java.io.*;
import java.util.LinkedList;

public class UserPreferences implements Serializable {
    private BitcoinItem bitcoinItem;
    private DollarItem dollarItem;
    private String guildId, preferredChannelId;
    private int variationFilter = 3;

    // February 19th Update
    private LinkedList<Reminder> reminders;
    private LinkedList<Exam> exams;
    private LinkedList<Subject> subjects;

    public UserPreferences() { }

    public UserPreferences(String guildId) {
        this.guildId = guildId;
        save();
    }

    public static UserPreferences load(String guildId){
        try {
            File file = new File(guildId);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            return (UserPreferences) ois.readObject();
        } catch (IOException e){
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e){
            System.err.println("Incorrect saved object");
            e.printStackTrace();
        }

        return new UserPreferences(guildId);
    }

    public BitcoinItem getBitcoinItem() {
        return bitcoinItem;
    }

    public void setBitcoinItem(BitcoinItem bitcoinItem) {
        this.bitcoinItem = bitcoinItem;
        save();
    }

    public DollarItem getDollarItem() {
        return dollarItem;
    }

    public void setDollarItem(DollarItem dollarItem) {
        this.dollarItem = dollarItem;
        save();
    }

    public int getVariationFilter() {
        return variationFilter;
    }

    public void setVariationFilter(int variationFilter) {
        this.variationFilter = variationFilter;
        save();
    }

    public String getPreferredChannelId() {
        return preferredChannelId;
    }

    public void setPreferredChannelId(String preferredChannelId) {
        this.preferredChannelId = preferredChannelId;
        save();
    }

    public void addReminder(Reminder reminder){
        if (reminders == null) reminders = new LinkedList<>();
        reminders.add(reminder);
        save();
    }

    public void addExam(Exam exam){
        if (exams == null) exams = new LinkedList<>();
        exams.add(exam);
        save();
    }

    public void addSubject(Subject subject){
        if (subjects == null) subjects = new LinkedList<>();
        subjects.add(subject);
        save();
    }

    public LinkedList<Reminder> getReminders() {
        return reminders;
    }

    public LinkedList<Exam> getExams() {
        return exams;
    }

    public LinkedList<Subject> getSubjects() {
        return subjects;
    }

    public void save(){
        try {
            System.out.println("Saving preferences");
            IOUtils.save(this, guildId);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
