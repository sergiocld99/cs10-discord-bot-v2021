package cs10.discord.bot.v2021.common;

public enum Emoji {
    INCREASING(":chart_with_upwards_trend:"),
    DECREASING(":chart_with_downwards_trend:"),
    COIN(":coin:"), DOLLAR(":money_with_wings:");

    private final String codename;

    Emoji(String codename) {
        this.codename = codename;
    }

    public String getCodename() {
        return codename;
    }
}
