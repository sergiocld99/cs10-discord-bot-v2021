package cs10.discord.bot.v2021.v1.init;

import cs10.discord.bot.v2021.io.UserPreferences;
import cs10.discord.bot.v2021.v1.guild.GuildStatus;
import cs10.discord.bot.v2021.v1.listener.BotListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Init {
    private static final String TOKEN_PATH = "C:/KeyStore/cs10-discord-bot-v2021.token";
    private static final String INVITE_URL = "https://discord.com/api/oauth2/authorize?client_id=798471325847912448&permissions=3148864&scope=bot";

    // HERE ALL STARTS
    public static void startBot() throws IOException, LoginException,
            InterruptedException, AWTException {

        // Read protected token in local device
        String token = readToken();

        // Set Bot Properties (as Status and Permissions)
        JDA jda = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES)
                .setActivity(Activity.watching("el mercado (?"))
                .disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE).build();

        // Add Upcoming events listener
        BotListener listener = new BotListener();
        jda.addEventListener(listener);

        // Await for connection
        jda.awaitReady();

        // Show invitation link if is empty, or config listener if not
        if (jda.getGuilds().isEmpty())
            System.out.println("Bot loaded, you can invite him using " + INVITE_URL);
        else for (Guild guild : jda.getGuilds())
            listener.addGuildStatus(buildGuildStatus(guild));

        // Show notification
        new BotTrayIcon();
    }

    public static String readToken() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(TOKEN_PATH));
        String line = reader.readLine();
        reader.close();
        return line;
    }

    public static GuildStatus buildGuildStatus(Guild guild){
        UserPreferences preferences = UserPreferences.load(guild.getId());

        // First time in a server
        if (preferences.getPreferredChannelId() == null){
            TextChannel channel = guild.getDefaultChannel();

            if (channel != null && channel.canTalk()){
                preferences.setPreferredChannelId(channel.getId());
            } else System.err.println("Channel undefined or without talk permissions");
        }

        return new GuildStatus(guild, preferences);
    }


}
