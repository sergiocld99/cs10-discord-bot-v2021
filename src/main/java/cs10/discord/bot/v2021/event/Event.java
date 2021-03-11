package cs10.discord.bot.v2021.event;

import java.io.Serializable;

public abstract class Event implements Serializable {
    private long timestamp;     // When it'll begin
    private int duration;
    private final InstantRelativeMsg[] instants;

    public Event(long timestamp, int duration, InstantRelativeMsg... instants) {
        this.instants = instants;
        this.timestamp = timestamp;
        this.duration = duration;
    }

    public abstract String getInstantMsg(InstantRelativeMsg instant);

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
