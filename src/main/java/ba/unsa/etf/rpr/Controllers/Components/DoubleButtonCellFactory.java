package ba.unsa.etf.rpr.Controllers.Components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
/**
 * Double button cell factory for creation of buttons for each cell in the table
 * @param <Type>
 * */
public class DoubleButtonCellFactory<Type> implements Callback<TableColumn<Type, Type>, TableCell<Type, Type>> {
    private final EventHandler<ActionEvent> buttonOne;
    private final EventHandler<ActionEvent> buttonTwo;
    /**
     * Creates buttons edit and delete respectively for two given event handlers
     * @param buttonOne event handler for first button - Edit
     * @param buttonTwo event handler for second button - Delete
     * */
    public DoubleButtonCellFactory(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo) {
        this.buttonOne = buttonOne;
        this.buttonTwo = buttonTwo;
    }

    @Override
    public TableCell<Type, Type> call(TableColumn<Type, Type> typeTypeTableColumn) {
        return new DoubleButtonTableCell<>(buttonOne, buttonTwo);
    }
}
