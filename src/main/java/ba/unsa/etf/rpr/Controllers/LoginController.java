package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * Controller managing Login
 * @author Hana Catic
 * */
public class LoginController {

    //components
    public Button loginBtn;

    /**
     * Opens admin stage that allows overview of all journeys, trains and railway stations and allows editing
     * */
    public void actionLogin(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/journeys.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.show();
        Stage lastStage = (Stage) loginBtn.getScene().getWindow();
        lastStage.close();
    }
}
