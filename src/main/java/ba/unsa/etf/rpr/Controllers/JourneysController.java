package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.domain.Journey;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.Time;

public class JourneysController {
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
}
