package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Bussiness.TrainManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Train;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
/**
 * Controller managing Admin Train Form
 * @author Hana Catic
 * */
public class TrainController {
    //manager
    private final TrainManager trainManager = new TrainManager();
    //components
    public TextField trainName;
    public DatePicker trainDateBought;
    public TableView trainsTable;
    public TableColumn<Train, String> idColumn;
    public TableColumn<Train, String> nameColumn;
    public TableColumn<Train, Date> dateBoughtColumn;


    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Train, String>("name"));
        dateBoughtColumn.setCellValueFactory(new PropertyValueFactory<Train, Date>("dateBought"));
        refreshTrains();
        trainsTable.getSelectionModel().selectedItemProperty().addListener((obs, o, n)->{
            if(n != null){
                    trainName.setText(((Train) n).getName());
                    trainDateBought.setValue(((Train) n).getDateBought().toLocalDate());
                }
            });
    }
    /**
     * fetches trains from database
     * */
    private void refreshTrains(){
        try{
            trainsTable.setItems(FXCollections.observableList(trainManager.getAll()));
            trainsTable.refresh();
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void addTrain(ActionEvent actionEvent) {
        try{
            Train train = new Train();
            train.setName(trainName.getText());
            train.setDateBought(Date.valueOf(trainDateBought.getValue()));
            train = trainManager.add(train);
            refreshTrains();
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }


    public void updateTrain(ActionEvent actionEvent) {
        try{
            Train train = (Train) trainsTable.getSelectionModel().getSelectedItem();
            train.setName(trainName.getText());
            train.setDateBought(Date.valueOf(trainDateBought.getValue()));
            train = trainManager.update(train);
            refreshTrains();
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void deleteTrain(ActionEvent actionEvent) {
        try{
            Train train = (Train) trainsTable.getSelectionModel().getSelectedItem();
            trainManager.delete(train.getId());
            refreshTrains();
        } catch (RailwayException e) {
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
