
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ArrayList<User> users = new ArrayList<>();

        // Read users.txt and keep only valid users.
        try (Scanner reader = new Scanner(new File("users.txt"))) {

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.trim().split("\\s+");

                if (parts.length != 2) {
                    System.err.println(
                            line + " - Expected username and password"
                    );
                    continue;
                }

                try {
                    User user = new User(parts[0], parts[1]);
                    users.add(user);

                } catch (IllegalArgumentException e) {
                    System.err.println(line + " " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Could not open users.txt");
            return;
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login.fxml")
        );

        Scene scene = new Scene(loader.load());

        LoginController controller = loader.getController();
        controller.setUsers(users);

        stage.setTitle("Users Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
