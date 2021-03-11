package cs10.discord.bot.v2021.v1.common;

import cs10.discord.bot.v2021.v1.exception.Dialog;

public class Simplifier {
    private static final char[] MIN_VOCALS = new char[]{'a', 'e', 'i', 'o', 'u'};
    private static final char[] MIN_ACCENTS = new char[]{'á', 'é', 'í', 'ó', 'ú'};

    public static String with(String s){
        return removeAccents(s.toLowerCase());
    }

    private static String removeAccents(String s){
        // Replace for each vocal (lowercase was applied)
        for (int i=0; i< MIN_VOCALS.length; i++){
            s = s.replace(MIN_ACCENTS[i], MIN_VOCALS[i]);
        }

        return s;
    }

    public static int toPositiveNumber(String s){
        try {
            int result = Integer.parseInt(s);
            if (result >= 0) return result;
            throw new IllegalArgumentException(s + " is a negative number");
        } catch (Exception e) {
            Dialog.error(e.getMessage());
            e.printStackTrace();
        }

        return -1;
    }
}
