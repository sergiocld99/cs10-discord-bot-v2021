package cs10.discord.bot.v2021.v1.common;

public enum Emoji {
    INCREASING(":chart_with_upwards_trend:"),
    DECREASING(":chart_with_downwards_trend:"),
    COIN(":coin:"), DOLLAR(":money_with_wings:"),
    GRINNING(":grinning:"), WINK(":wink:"),
    GOOD_LUCK("four_leaf_clover"), CALENDAR(":calendar:"),
    BELL(":bell:"), EXCUSE_ME(":face_with_raised_eyebrow:"),
    DIED(":dizzy_face:"), BOOKS(":books:");

    private final String codename;

    Emoji(String codename) {
        this.codename = codename;
    }

    public String getCodename() {
        return codename;
    }
}
