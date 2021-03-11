package cs10.discord.bot.v2021.currency.bitcoin;

import cs10.discord.bot.v2021.v1.common.Emoji;
import cs10.discord.bot.v2021.currency.Currency;
import cs10.discord.bot.v2021.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

public class Bitcoin extends Currency {
    private static final String SOURCE_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Override
    public double getPriceAsDouble() throws IOException {
        return getPrice().doubleValue();
    }

    public static BigDecimal getPrice() throws IOException {
        JSONObject jsonObject = IOUtils.readJsonFromUrl(SOURCE_URL);
        return jsonObject.getJSONObject("bpi").getJSONObject("USD")
                .getBigDecimal("rate_float");
    }

    @Override
    public String getName() {
        return "Bitcoin";
    }

    @Override
    public String getUnit() {
        return "USD";
    }

    @Override
    public Emoji getEmoji() {
        return Emoji.COIN;
    }
}
