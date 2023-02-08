package ba.unsa.etf.rpr.Controllers.Components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DoubleButtonCellFactory<Type> implements Callback<TableColumn<Type, Type>, TableCell<Type, Type>> {
    private final EventHandler<ActionEvent> buttonOne;
    private final EventHandler<ActionEvent> buttonTwo;

    public DoubleButtonCellFactory(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo) {
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    @Override
    public TableCell<Type, Type> call(TableColumn<Type, Type> typeTypeTableColumn) {
        return new DoubleButtonTableCell<>(buttonOne, buttonTwo);
    }
}
