package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Bussiness.JourneyManager;
import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Bussiness.TrainManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class JourneyFormController {
    private final TrainManager trainManager = new TrainManager();
    private final JourneyManager journeyManager = new JourneyManager();
    private final RailwayStationManager stationManager = new RailwayStationManager();
    private JourneyModel model = new JourneyModel();
    private Integer journeyId = null;
    public ComboBox<Train> trainName;
    public ComboBox<RailwayStation> departureStation;
    public ComboBox<RailwayStation> arrivalStation;
    public DatePicker departureDate;
    public DatePicker arrivalDate;
    public Spinner<Integer> departureTimeH;
    public Spinner<Integer> departureTimeMin;
    public Spinner<Integer> arrivalTimeH;
    public Spinner<Integer> arrivalTimeMin;

    public JourneyFormController(Integer journeyId){
        this.journeyId = journeyId;
    }

    public void initialize(){
        try{
            trainName.setItems(FXCollections.observableList(trainManager.getAll()));
            departureStation.setItems(FXCollections.observableList(stationManager.getAll()));
            arrivalStation.setItems(FXCollections.observableList(stationManager.getAll()));
            trainName.valueProperty().bindBidirectional(model.train);
            departureStation.valueProperty().bindBidirectional(model.departureStation);
            arrivalStation.valueProperty().bindBidirectional(model.arrivalStation);
            departureDate.valueProperty().bindBidirectional(model.departureDate);
            arrivalDate.valueProperty().bindBidirectional(model.arrivalDate);
            departureTimeH.getValueFactory().valueProperty().bindBidirectional(model.departureTimeHH);
            departureTimeMin.getValueFactory().valueProperty().bindBidirectional(model.departureTimeMM);
            arrivalTimeH.getValueFactory().valueProperty().bindBidirectional(model.arrivalTimeHH);
            arrivalTimeMin.getValueFactory().valueProperty().bindBidirectional(model.arrivalTimeMM);
            if(journeyId != null){
                journeyManager.getById(journeyId);
            }
        }
        catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void addJourney(ActionEvent actionEvent) {
        try{
        Journey journey = model.toJourney();
        journeyManager.add(journey);
        } catch (RailwayException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateJourney(ActionEvent actionEvent) {
    }

    public void deleteJourney(ActionEvent actionEvent) {
    }
    public class JourneyModel{
        public SimpleObjectProperty<Train> train = new SimpleObjectProperty<Train>();
        public SimpleObjectProperty<RailwayStation> departureStation = new SimpleObjectProperty<RailwayStation>();
        public SimpleObjectProperty<RailwayStation> arrivalStation = new SimpleObjectProperty<RailwayStation>();
        public SimpleObjectProperty<LocalDate> departureDate = new SimpleObjectProperty<LocalDate>();
        public SimpleObjectProperty<LocalDate> arrivalDate = new SimpleObjectProperty<LocalDate>();
        public SimpleObjectProperty<Integer> departureTimeHH = new SimpleObjectProperty<Integer>();
        public SimpleObjectProperty<Integer> departureTimeMM = new SimpleObjectProperty<Integer>();
        public SimpleObjectProperty<Integer> arrivalTimeHH = new SimpleObjectProperty<Integer>();
        public SimpleObjectProperty<Integer> arrivalTimeMM = new SimpleObjectProperty<Integer>();

        public Journey toJourney(){
            Journey journey = new Journey();
            journey.setTrain(this.train.getValue());
            journey.setDepartureStation(this.departureStation.getValue());
            journey.setArrivalStation(this.arrivalStation.getValue());
            journey.setDepartureDate(Date.valueOf(this.departureDate.getValue()));
            journey.setArrivalDate(Date.valueOf(this.arrivalDate.getValue()));
            journey.setDepartureTime(new Time(this.departureTimeHH.getValue(), this.departureTimeMM.getValue(),
                    0));
            journey.setArrivalTime(new Time(this.arrivalTimeHH.getValue(), this.arrivalTimeMM.getValue(),0));
            return journey;
        }
    }
}
