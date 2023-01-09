package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Bussiness.TrainManager;
import ba.unsa.etf.rpr.domain.Train;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

public class TrainController {
    private final TrainManager trainManager = new TrainManager();

    public TextField trainName;
    public DatePicker trainDateBought;


    /*public void initialize(){
        /*idColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("name"));
        dateBoughtColumn.setCellValueFactory(new PropertyValueFactory<Train, Date>("date bought"));
    }*/
}
