package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.RailwayStation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    public Button adminBtn;
    public TableColumn duration;
    public TableColumn arrivalTime;
    public TableColumn arrivalDate;
    public TableColumn departureTime;
    public TableColumn departureDate;
    public TableColumn departureStation;
    public TableColumn arrivalStation;
    public ToggleGroup departureArrival;
    public RadioButton arrive;
    public RadioButton depart;
    public Spinner timeMM;
    public Spinner timeHH;
    public DatePicker date;
    public ComboBox<RailwayStation> arrival;
    public ComboBox<RailwayStation> departure;
    RailwayStationManager stationManager = new RailwayStationManager();
    public void initialize() {
        try {
            arrival.setItems(FXCollections.observableList(stationManager.getAll()));
            departure.setItems(FXCollections.observableList(stationManager.getAll()));
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void actionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void openLogin(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.setTitle("Admin");
        stage.setMinHeight(90);
        stage.setMinWidth(120);
        stage.setResizable(false);
        stage.show();
        Stage lastStage = (Stage) adminBtn.getScene().getWindow();
        lastStage.close();
    }
}
