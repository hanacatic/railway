package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.Bussiness.JourneyManager;
import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * Controller managing Home Form, User Form
 * @author Hana Catic
 * */
public class HomeController {
    //managers
    JourneyManager journeyManager = new JourneyManager();
    RailwayStationManager stationManager = new RailwayStationManager();
    //components
    public TableColumn<Journey, Time> duration;
    public TableColumn<Journey, Time> arrivalTime;
    public TableColumn<Journey, Date> arrivalDate;
    public TableColumn<Journey, Time> departureTime;
    public TableColumn<Journey, Date> departureDate;
    public TableColumn<Journey, String> departureStation;
    public TableColumn<Journey, String> arrivalStation;
    public ToggleGroup departureArrival;
    public RadioButton arrive;
    public RadioButton depart;
    public Spinner<Integer> timeMM;
    public Spinner<Integer> timeHH;
    public DatePicker date;
    public ComboBox<RailwayStation> arrival;
    public ComboBox<RailwayStation> departure;
    public TableView journeyTable;

    public void initialize() {
        try {
            arrival.setItems(FXCollections.observableList(stationManager.getAll()));
            departure.setItems(FXCollections.observableList(stationManager.getAll()));
            departureStation.setCellValueFactory(new PropertyValueFactory<Journey, String>("departureStation"));
            arrivalStation.setCellValueFactory(new PropertyValueFactory<Journey, String>("arrivalStation"));
            departureDate.setCellValueFactory(new PropertyValueFactory<Journey, Date>("departureDate"));
            arrivalDate.setCellValueFactory(new PropertyValueFactory<Journey, Date>("arrivalDate"));
            departureTime.setCellValueFactory(new PropertyValueFactory<Journey, Time>("departureTime"));
            arrivalTime.setCellValueFactory(new PropertyValueFactory<Journey, Time>("arrivalTime"));
            duration.setCellValueFactory(c->new SimpleObjectProperty<Time>(new Time(c.getValue().getArrivalTime().getTime()
            - c.getValue().getDepartureTime().getTime())));
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Refreshes journeys in the table based on given collection of journeys
     * @param collection of journeys to be shown in the table
     * */
    private void refreshJourneys(List<Journey> collection){
        journeyTable.setItems(FXCollections.observableList(collection));
        journeyTable.refresh();
    }
    /**
     * Opens Admin Log In form
     * */
    public void openLogin(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene( fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.setTitle("Admin");
        stage.setMinHeight(90);
        stage.setMinWidth(120);
        stage.setResizable(false);
        stage.show();
        Stage lastStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        lastStage.close();
    }
    /**
     * Searches journeys in database based on the form - chosen departure station, arrival station, date of journey, the earliest time of journey
     * */
    public void searchJourneys(ActionEvent actionEvent){
        try {
            List<Journey> journeys = journeyManager.search(departure.getValue(), arrival.getValue(), Date.valueOf(date.getValue()), new Time(this.timeHH.getValue(), this.timeMM.getValue(), 0), arrive.isSelected());
            refreshJourneys(journeys);
        }
        catch(RailwayException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK ).show();
        }
    }
}
