package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AppFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Prijava!");
        stage.setScene(scene);
        stage.setMinHeight(90);
        stage.setMinWidth(120);
        //stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}