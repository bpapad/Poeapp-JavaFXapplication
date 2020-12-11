package main.classes;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class HyperlinkCell implements  Callback<TableColumn<BuildGuide, Hyperlink>, TableCell<BuildGuide, Hyperlink>> {
    /*
        The first type parameter of the Callback represents what object is passed as a parameter in the call() method and the second represents what object is returned.
        So using this Callback, for every cell in the TableColumn the below method will be called. This sets the Hyperlink as graphic instead of a default String.
     */
    @Override
    public TableCell<BuildGuide, Hyperlink> call(TableColumn<BuildGuide, Hyperlink> arg) {
        return new TableCell<>() {
            @Override
            protected void updateItem(Hyperlink item, boolean empty) {
                setGraphic(item);
            }
        };
    }

}
