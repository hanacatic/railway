package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.RailwayStation;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class RailwayStationController {
    private final RailwayStationManager stationManager = new RailwayStationManager();
    public TextField stationName;
    public TextField stationAddress;
    public TextField stationCity;
    public TextField stationCountry;
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
        refreshStations();
        stationsTable.getSelectionModel().selectedItemProperty().addListener((obs, o, n)->{
            if(n != null){
                stationName.setText(((RailwayStation) n).getName());
                stationAddress.setText(((RailwayStation) n).getAddress());
                stationCity.setText(((RailwayStation) n).getCity());
                stationCountry.setText(((RailwayStation) n).getCountry());
            }
        });
    }

    private void refreshStations(){
        try{
            stationsTable.setItems(FXCollections.observableList(stationManager.getAll()));
            stationsTable.refresh();
        }catch(RailwayException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    public void addStation(ActionEvent actionEvent) {
        try{
            RailwayStation station = new RailwayStation();
            station.setName(stationName.getText());
            station.setAddress(stationAddress.getText());
            station.setCity(stationCity.getText());
            station.setCountry(stationCountry.getText());
            station = stationManager.add(station);
            refreshStations();
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void updateStation(ActionEvent actionEvent) {
    }

    public void deleteStation(ActionEvent actionEvent) {
        try{
            RailwayStation station = (RailwayStation) stationsTable.getSelectionModel().getSelectedItem();
            stationManager.delete(station.getId());
            refreshStations();
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
