package cs10.discord.bot.v2021.io;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class IOUtils {

    public static JSONObject readJsonFromUrl(String url) throws IOException {
        return new JSONObject(readUrl(url));
    }

    public static String readUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        response.close();
        return body;
    }

    public static void save(Serializable serializable, String filename) throws IOException {
        File file = new File(filename);
        System.out.println("Saving in " + file.getAbsolutePath());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(serializable);
        oos.close();
    }
}
