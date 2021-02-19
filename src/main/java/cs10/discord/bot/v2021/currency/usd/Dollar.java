package cs10.discord.bot.v2021.currency.usd;

import cs10.discord.bot.v2021.common.Emoji;
import cs10.discord.bot.v2021.currency.Currency;
import cs10.discord.bot.v2021.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Dollar extends Currency {
    private static final String SOURCE_URL = "https://www.dolarhoy.com/";

    @Override
    public double getPriceAsDouble() throws IOException {
        return Double.parseDouble(getPrice().replace("$ ","")
                .replace(",","."));
    }

    public static String getPrice() throws IOException {
        String body = IOUtils.readUrl(SOURCE_URL);
        Document doc = Jsoup.parse(body);
        Elements elements = doc.body().getElementById("sitio").getElementsByClass("venta");
        return elements.get(0).getElementsByClass("val").text().replace("$","");
    }

    @Override
    public String getName() {
        return "Dollar Blue";
    }

    @Override
    public String getUnit() {
        return "ARS";
    }

    @Override
    public Emoji getEmoji() {
        return Emoji.DOLLAR;
    }
}
