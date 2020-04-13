package Game;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable
{
    public Button registerButton;
    public TextField enterFirstName;
    public TextField enterLastName;
    public TextField enterUsername;
    public TextField enterPassword;
    public TextField enterConfirmPassword;
    public Label errorLabel;

    public void onRegisterClicked()
    {
        if(!enterFirstName.getText().equals("") && !enterLastName.getText().equals("") && !enterUsername.getText().equals("") &&
                !enterPassword.getText().equals("") && !enterConfirmPassword.getText().equals("") &&
                enterPassword.getText().equals(enterConfirmPassword.getText()))
        {
            //Send off data to the Player class to be sent to the Server through a thread
            try
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../sample/Login.fxml"));
                Parent root = loader.load();
                LoginController lc = loader.getController();
                Stage stage = (Stage) registerButton.getScene().getWindow();
                stage.close();
                stage.setTitle("Login");
                stage.setScene(new Scene(root));
                stage.show();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            StringBuffer error = new StringBuffer();
            if(enterFirstName.getText().equals(""))
            {
                error.append("Please enter your first name!\n");
            }
            if(enterLastName.getText().equals(""))
            {
                error.append("Please enter your last name!\n");
            }
            if(enterUsername.getText().equals(""))
            {
                error.append("Please enter a valid username!\n");
            }
            if(enterPassword.getText().equals(""))
            {
                error.append("Please enter a valid password!\n");
            }
            if(!enterPassword.getText().equals(enterConfirmPassword.getText()) && !enterPassword.getText().equals(""))
            {
                error.append("Passwords do NOT match!\n");
            }

            errorLabel.setText(error.toString());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
