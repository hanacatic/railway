package ba.unsa.etf.rpr.Controllers.Components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
/**
 * Custom component for rendering table cell with two buttons (Edit and Delete)
 * @param <Type>  Bean class represented in the table cells
 * */
public class DoubleButtonTableCell<Type> extends TableCell<Type, Type> {
    private Button edit;
    private Button delete;
    /**
     * Default constructor
     * @param buttonOne event handler for action on first button - Edit
     * @param buttonTwo event handler for action on second button - Delete
     * */
    public DoubleButtonTableCell(EventHandler<ActionEvent> buttonOne, EventHandler<ActionEvent> buttonTwo) {
        edit = new Button("Edit");
        edit.setOnAction(buttonOne);
        delete = new Button("Delete");
        delete.setOnAction(buttonTwo);
    }

    @Override
    protected void updateItem(Type o, boolean b) {
        super.updateItem(o, b);
        if (o != null) {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            edit.setUserData(o);
            delete.setUserData(o);
            box.getChildren().add(edit);
            box.getChildren().add(delete);
            setGraphic(box);
        }
    }
}
