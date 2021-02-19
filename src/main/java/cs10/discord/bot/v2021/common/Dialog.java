package cs10.discord.bot.v2021.common;

import javax.swing.*;

public class Dialog {

    public static void error(String msg){
        JOptionPane.showMessageDialog(null, msg,
                "cs10 - v2021 Bot", JOptionPane.ERROR_MESSAGE);
    }
}
