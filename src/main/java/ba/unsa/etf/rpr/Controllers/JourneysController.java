package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.App;
import ba.unsa.etf.rpr.Bussiness.JourneyManager;
import ba.unsa.etf.rpr.Controllers.Components.DoubleButtonCellFactory;
import ba.unsa.etf.rpr.Controllers.Components.DoubleButtonTableCell;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Journey;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
/**
 * Controller for managing Journey Entities
 * @author Hana Catic
 * */
public class JourneysController {
    //manager
    private final JourneyManager journeyManager = new JourneyManager();
    //components
    public TableView journeysTable;
    public TableColumn<Journey, String> idColumn;
    public TableColumn<Journey, String> trainIdColumn;
    public TableColumn<Journey, String> departureStationIdColumn;
    public TableColumn<Journey, String> arrivalStationIdColumn;
    public TableColumn<Journey, Date> departureDateColumn;
    public TableColumn<Journey, Time> departureTimeColumn;
    public TableColumn<Journey, Date> arrivalDateColumn;
    public TableColumn<Journey, Time> arrivalTimeColumn;
    public TableColumn<Journey, Integer> actionColumn;

    public BorderPane journeyScreen;

    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Journey, String>("id"));
        trainIdColumn.setCellValueFactory(new PropertyValueFactory<Journey, String>("train"));
        departureStationIdColumn.setCellValueFactory(new PropertyValueFactory<Journey, String>("departureStation"));
        arrivalStationIdColumn.setCellValueFactory(new PropertyValueFactory<Journey, String>("arrivalStation"));
        departureDateColumn.setCellValueFactory(new PropertyValueFactory<Journey, Date>("departureDate"));
        departureTimeColumn.setCellValueFactory(new PropertyValueFactory<Journey, Time>("departureTime"));
        arrivalDateColumn.setCellValueFactory(new PropertyValueFactory<Journey, Date>("arrivalDate"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Journey, Time>("arrivalTime"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<Journey, Integer>("id"));

        actionColumn.setCellFactory(new DoubleButtonCellFactory(editEvent ->{
            int journeyId = Integer.parseInt(((Button) editEvent.getSource()).getUserData().toString());
            editJourneyScene(journeyId);
        }, (deleteEvent->{
            int journeyId = Integer.parseInt(((Button) deleteEvent.getSource()).getUserData().toString());
            deleteJourney(journeyId);
        })));

        refreshJourneys();
    }
    /**
     * fetches journeys from database
     * */
    private void refreshJourneys(){
        try{
            journeysTable.setItems(FXCollections.observableList(journeyManager.getAll()));
            journeysTable.refresh();
        } catch(RailwayException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * open form for editing or creating trains or railway stations
     * @param fxmlName - name of scene resource
     * @param sceneName - name of scene
     * */
    public void editingScene(String fxmlName, String sceneName){
        try{
            ((Stage)journeyScreen.getScene().getWindow()).hide();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxmlName + ".fxml"));
            Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(sceneName);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding(event ->{
                ((Stage)journeyScreen.getScene().getWindow()).show();
                refreshJourneys();;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens form for editing or creating journeys
     * @param journeyId - only for editing, only if the journey already exists, otherwise null
     * */
    public void editJourneyScene(Integer journeyId) {
        try{
            ((Stage)journeyScreen.getScene().getWindow()).hide();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/JourneyForm.fxml"));
            fxmlLoader.setController(new JourneyFormController(journeyId));
            Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Edit Journey");
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding(event ->{
                ((Stage)journeyScreen.getScene().getWindow()).show();
                refreshJourneys();;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Event handler for journey deletion with alert to confirm intention.
     * @param journeyId
     * */
    public void deleteJourney(Integer journeyId){
        try{
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this journey?");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(!result.get().getButtonData().isCancelButton()){
                journeyManager.delete(journeyId);
                refreshJourneys();
            }
        }catch(RailwayException e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
    /**
     * Event handler for creating of a journey
     * */
    public void addJourney(ActionEvent actionEvent) {
        editJourneyScene(null);
    }
    /**
     * Event handler for opening train section.
     * */

    public void editTrains(ActionEvent actionEvent) throws IOException {
        editingScene("train", "Edit Trains");
    }
    /**
     * Event handler for opening railway station section.
     * */
    public void editRailwayStations(ActionEvent actionEvent) throws IOException {
        editingScene("railwayStation", "Edit Railway Stations");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/home.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.show();
        Stage lastStage = (Stage) journeysTable.getScene().getWindow();
        lastStage.close();
    }
}
