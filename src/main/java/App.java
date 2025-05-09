import service.UserInterface;

import static app.InputManager.cleanUp;

public class App {
    public static void main(String[] args) {
        cleanUp();
        new UserInterface().display();
    }
}
