package cs10.discord.bot.v2021.event.reminder;

import cs10.discord.bot.v2021.event.Event;
import cs10.discord.bot.v2021.event.InstantRelativeMsg;

import java.io.Serializable;

public class Reminder extends Event implements Serializable {
    private final String message;

    public Reminder(long timestamp, String channelId, String message){
        super(timestamp, 0, channelId, InstantRelativeMsg.START);
        this.message = message;
    }

    @Override
    public String getInstantMsg(InstantRelativeMsg instant) {
        if (instant == InstantRelativeMsg.START) return message;
        return null;
    }
}
