package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Bussiness.JourneyManager;
import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Bussiness.TrainManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Train;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class JourneyFormController {
    private final TrainManager trainManager = new TrainManager();
    private final JourneyManager journeyManager = new JourneyManager();
    private final RailwayStationManager stationManager = new RailwayStationManager();
    private Integer journeyId = null;
    public ComboBox<Train> trainName;
    public ComboBox departureStation;
    public ComboBox arrivalStation;
    public DatePicker departureDate;
    public DatePicker arrivalDate;
    public Spinner departureTimeH;
    public Spinner departureTimeMin;
    public Spinner arrivalTimeH;
    public Spinner arrivalTimeMin;

    public JourneyFormController(Integer journeyId){
        this.journeyId = journeyId;
    }

    public void initialize(){
        try{
            trainName.setItems(FXCollections.observableList(trainManager.getAll()));
            departureStation.setItems(FXCollections.observableList(stationManager.getAll()));
            arrivalStation.setItems(FXCollections.observableList(stationManager.getAll()));
            if(journeyId != null){
                journeyManager.getById(journeyId);
            }
        }
        catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void addJourney(ActionEvent actionEvent) {
    }

    public void updateJourney(ActionEvent actionEvent) {
    }

    public void deleteJourney(ActionEvent actionEvent) {
    }
}
