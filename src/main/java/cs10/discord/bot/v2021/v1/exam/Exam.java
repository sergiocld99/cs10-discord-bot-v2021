package cs10.discord.bot.v2021.v1.exam;

import cs10.discord.bot.v2021.event.DateUtils;
import cs10.discord.bot.v2021.event.Event;
import cs10.discord.bot.v2021.event.InstantRelativeMsg;
import cs10.discord.bot.v2021.v1.common.Emoji;

import java.io.Serializable;
import java.util.Date;

public class Exam extends Event implements Serializable {
    private final String topic;

    public Exam(long timestamp, String channelId, String topic){
        super(timestamp, 3, channelId, InstantRelativeMsg.values());
        this.topic = topic;
    }

    @Override
    public String getInstantMsg(InstantRelativeMsg instant) {
        switch (instant){
            case PREVIOUS_DAY:
                return "Mañana es el examen de **" + topic +
                    "** a las **" + DateUtils.getTimeOfDay(getTimestamp()) + "**";
            case TEN_MINUTES_BEFORE:
                return "En 10 minutos arranca el examen de **" + topic + "** \n\n" +
                    "Revisá tener la compu con carga, y de tener los machetes a mano " + Emoji.WINK.getCodename();
            case START:
                return "¡Comienza el examen! Éxitos! " + Emoji.GOOD_LUCK.getCodename() + '\n' +
                    "Recuerden revisar antes de entregar (si alcanza el tiempo, claro)";
            case END:
                return "¡Se acabó el examen! Espero que les haya ido bien " + Emoji.GRINNING.getCodename();
        }

        return null;
    }

    @Override
    public String toString() {
        return DateUtils.format(new Date(getTimestamp())) + " - " + topic;
    }
}
