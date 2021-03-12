package cs10.discord.bot.v2021.event;

import java.io.Serializable;

public abstract class Event implements Serializable {
    private long timestamp;     // When it'll begin
    private int duration;
    private final InstantRelativeMsg[] instants;
    private String channelId;

    public Event(long timestamp, int duration, String channelId, InstantRelativeMsg... instants) {
        this.instants = instants;
        this.timestamp = timestamp;
        this.duration = duration;
        this.channelId = channelId;
    }

    public abstract String getInstantMsg(InstantRelativeMsg instant);

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public InstantRelativeMsg[] getInstants() {
        return instants;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getDuration() {
        return duration;
    }
}
