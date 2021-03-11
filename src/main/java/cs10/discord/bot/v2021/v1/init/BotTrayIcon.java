package cs10.discord.bot.v2021.v1.init;

import java.awt.*;

public class BotTrayIcon {

    public BotTrayIcon() throws AWTException {
        if (SystemTray.isSupported()) {
            displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("cs10 Bot");
        tray.add(trayIcon);

        // Create a pop-up menu components
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        //Add components to pop-up menu
        final PopupMenu popup = new PopupMenu();
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        trayIcon.displayMessage("Hi! Bot is ready - v0.01 R71",
                "cs10 - Discord Bot v2021", TrayIcon.MessageType.INFO);
    }
}
