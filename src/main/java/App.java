import config.db.AppInitializer;
import ui.UserInterface;

import static ui.InputManager.readStringFromUser;

public class App {
    public static void main(String[] args) {
        System.out.println("[1]. File Writer");
        System.out.println("[2]. Database");

        switch (Integer.parseInt(readStringFromUser("Choice: "))) {
            case 1 -> new UserInterface().display();
            case 2 -> new AppInitializer().run(args);
        }
    }
}
