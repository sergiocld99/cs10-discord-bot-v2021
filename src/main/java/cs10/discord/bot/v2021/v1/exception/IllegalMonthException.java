package cs10.discord.bot.v2021.v1.exception;

import cs10.discord.bot.v2021.v1.common.Emoji;

public class IllegalMonthException extends IllegalArgumentException {

    public IllegalMonthException(String month){
        super(Emoji.CALENDAR.getCodename() + Emoji.FAIL.getCodename() +
                " - El mes " + month + " no es válido");
    }
}
