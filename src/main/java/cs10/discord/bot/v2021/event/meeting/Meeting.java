package cs10.discord.bot.v2021.event.meeting;

import cs10.discord.bot.v2021.event.Event;
import cs10.discord.bot.v2021.event.InstantRelativeMsg;

public class Meeting extends Event {
    private final String subject;

    public Meeting(long timestamp, String channelId, String subject){
        super(timestamp, 2, channelId, InstantRelativeMsg.TEN_MINUTES_BEFORE, InstantRelativeMsg.START);
        this.subject = subject;
    }

    @Override
    public String getInstantMsg(InstantRelativeMsg instant) {
        switch (instant) {
            case TEN_MINUTES_BEFORE:
                return "Hey! En 10 minutos arranca la clase de **" + subject + "**";
            case START:
                return "Empezó la clase de **" + subject + "**";
            default:
                return null;
        }
    }
}
