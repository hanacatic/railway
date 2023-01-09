package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Bussiness.TrainManager;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class TrainController {
    private final TrainManager trainManager = new TrainManager();

    public TextField trainName;
    public DatePicker trainDateBought;
}
