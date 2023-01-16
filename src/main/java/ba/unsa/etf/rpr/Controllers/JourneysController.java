package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.Bussiness.JourneyManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Journey;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class JourneysController {
    private final JourneyManager journeyManager = new JourneyManager();
    public TableView journeysTable;
    public TableColumn<Journey, String> idColumn;
    public TableColumn<Journey, String> trainIdColumn;
    public TableColumn<Journey, String> departureStationIdColumn;
    public TableColumn<Journey, String> arrivalStationIdColumn;
    public TableColumn<Journey, Date> departureDateColumn;
    public TableColumn<Journey, Time> departureTimeColumn;
    public TableColumn<Journey, Date> arrivalDateColumn;
    public TableColumn<Journey, Time> arrivalTimeColumn;

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Journey, String>("id"));
        trainIdColumn.setCellValueFactory(new PropertyValueFactory<Journey, String>("train"));
        departureStationIdColumn.setCellValueFactory(new PropertyValueFactory<Journey, String>("departureStation"));
        arrivalStationIdColumn.setCellValueFactory(new PropertyValueFactory<Journey, String>("arrivalStation"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<Journey, Date>("departureDate"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Journey, Time>("departureTime"));
        arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<Journey, Date>("arrivalDate"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Journey, Time>("arrivalTime"));
    }

    private void refreshJourneys(){
        try{
            journeysTable.setItems(FXCollections.observableList(journeyManager.getAll()));
            journeysTable.refresh();
        } catch(RailwayException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void addJourney(ActionEvent actionEvent) {
    }

    public void editTrains(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/train.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.show();
    }

    public void editRailwayStations(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/railwayStation.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.show();
    }
}
