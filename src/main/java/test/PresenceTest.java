package test;

import cs10.discord.bot.v2021.v1.exception.Dialog;
import cs10.discord.bot.v2021.v1.init.Init;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;

public class PresenceTest {

    public static void main(String[] args) {
        try {
            Init.startBot();
        } catch (IOException e){
            Dialog.error("Unable to read token");
            System.exit(2);
        } catch (LoginException e){
            System.err.println("Unable to login: ");
            e.printStackTrace();
        } catch (InterruptedException e){
            Dialog.error("Bot interrupted");
            System.exit(3);
        } catch (AWTException e){
            System.err.println("Unable to set tray icon");
            System.exit(1);
        }
    }
}
