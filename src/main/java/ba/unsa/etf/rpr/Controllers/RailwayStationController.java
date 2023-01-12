package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.domain.RailwayStation;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RailwayStationController {
    private final RailwayStationManager stationManager = new RailwayStationManager();
    public TextField stationName;
    public TextField stationAddress;
    public ComboBox stationCity;
    public ComboBox stationCountry;
    public TableView stationsTable;
    public TableColumn<RailwayStation, String> idColumn;
    public TableColumn<RailwayStation, String> nameColumn;
    public TableColumn<RailwayStation, String> addressColumn;
    public TableColumn<RailwayStation, String> cityColumn;
    public TableColumn<RailwayStation, String> countryColumn;

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<RailwayStation, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<RailwayStation, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<RailwayStation, String>("address"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<RailwayStation, String>("city"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<RailwayStation, String>("country"));
    }

    private void refreshStations(){

    }
    public void addStation(ActionEvent actionEvent) {
    }

    public void updateStation(ActionEvent actionEvent) {
    }

    public void deleteStation(ActionEvent actionEvent) {
    }
}
