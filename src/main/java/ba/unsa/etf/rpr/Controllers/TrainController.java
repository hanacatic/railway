package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Bussiness.TrainManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Train;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

public class TrainController {
    private final TrainManager trainManager = new TrainManager();

    public TextField trainName;
    public DatePicker trainDateBought;
    public TableView trainsTable;
    public TableColumn<Train, String> idColumn;
    public TableColumn<Train, String> nameColumn;
    public TableColumn<Train, Date> dateBoughtColumn;


    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("name"));
        dateBoughtColumn.setCellValueFactory(new PropertyValueFactory<Train, Date>("date bought"));
    }

    private void refreshTrains(){
        try{
            trainsTable.setItems(FXCollections.observableList(trainManager.getAll()));
            trainsTable.refresh();
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
