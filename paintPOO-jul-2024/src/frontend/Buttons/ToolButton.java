package frontend.Buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import backend.model.Square;
import frontend.PaintPane;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;

public abstract class ToolButton extends ToggleButton  {

    public ToolButton(String text) {
        super(text);
        setMinWidth(90);
        setCursor(Cursor.HAND);
    }

    public void onMousePressed(PaintPane paintPane, double x, double y) {}

    public void onMouseReleased(PaintPane paintPane, double x, double y) {}

    public void onMouseClicked(PaintPane paintPane, double x, double y) {}

    public void onMouseDragged(PaintPane paintPane, double x, double y) {}


}
