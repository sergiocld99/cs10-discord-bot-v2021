package cs10.discord.bot.v2021.io;

import cs10.discord.bot.v2021.currency.bitcoin.BitcoinItem;
import cs10.discord.bot.v2021.currency.usd.DollarItem;
import cs10.discord.bot.v2021.v1.exam.Exam;
import cs10.discord.bot.v2021.event.reminder.Reminder;

import java.io.*;
import java.util.LinkedList;

public class UserPreferences implements Serializable {
    private BitcoinItem bitcoinItem;
    private DollarItem dollarItem;
    private String guildId, preferredChannelId;
    private int variationFilter = 3;

    // R69 Update
    private LinkedList<Reminder> reminders;
    private LinkedList<Exam> exams;

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

    public LinkedList<Reminder> getReminders() {
        if (reminders == null) reminders = new LinkedList<>();
        return reminders;
    }

    public LinkedList<Exam> getExams() {
        if (exams == null) exams = new LinkedList<>();
        return exams;
    }

    public void clearExams(){
        if (exams != null) exams.clear();
        save();
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
