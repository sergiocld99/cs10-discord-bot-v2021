package test;

import cs10.discord.bot.v2021.core.Init;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;

public class PresenceTest {

    public static void main(String[] args) {
        try {
            Init.startBot();
        } catch (IOException e){
            System.err.println("Unable to read token");
        } catch (LoginException e){
            System.err.println("Unable to login: ");
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (AWTException e){
            System.err.println("Unable to set tray icon");
            System.exit(1);
        }
    }
}
