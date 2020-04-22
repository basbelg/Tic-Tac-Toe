package Controllers;

import DataClasses.Spectator;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewSpectatorsController implements Initializable
{
    public ListView spectatorsList;
    public Button closeButton;

    public void onCloseClicked()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void passInfo(List<Spectator> spectators)
    {
        Platform.runLater(() -> {
            for (Spectator s : spectators) {
                spectatorsList.getItems().add(new Label(s.getUsername()));
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
