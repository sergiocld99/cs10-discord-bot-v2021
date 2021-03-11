package cs10.discord.bot.v2021.event.exam;

import cs10.discord.bot.v2021.v1.common.Emoji;
import cs10.discord.bot.v2021.event.DateUtils;
import cs10.discord.bot.v2021.event.Event;
import cs10.discord.bot.v2021.event.InstantRelativeMsg;

import java.io.Serializable;

public class Exam extends Event implements Serializable {
    private final String topic;

    public Exam(long timestamp, int duration, String topic){
        super(timestamp, duration, InstantRelativeMsg.values());
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
}
