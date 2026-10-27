
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorLabel;

    private ArrayList<User> users = new ArrayList<>();

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @FXML
    private void OnHelloButtonClick() {

        String email = username.getText();
        String pass = password.getText();

        if (email.isEmpty() || pass.isEmpty()) {
            errorLabel.setText("Please enter email and password");
            return;
        }

        for (User user : users) {

            if (user.getUsername().equals(email) &&
                    user.getPassword().equals(pass)) {

                errorLabel.setText("");

                try {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/welcome.fxml")
                    );

                    Scene scene = new Scene(loader.load());

                    Stage stage = (Stage) username.getScene().getWindow();

                    stage.setTitle("Welcome");
                    stage.setScene(scene);

                } catch (IOException e) {
                    errorLabel.setText("Could not open welcome screen");
                }

                return;
            }
        }

        errorLabel.setText("User or password do not match");
    }
}
