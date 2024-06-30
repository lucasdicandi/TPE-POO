package frontend.Buttons;

import backend.model.Point;
import frontend.PaintPane;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;

public abstract class ToolButton extends ToggleButton implements Button {
    public ToolButton(String text) {
        super(text);
        setMinWidth(90);
        setCursor(Cursor.HAND);
    }

    @Override
    public void onMousePressed(PaintPane paintPane, double x, double y) {}

    @Override
    public void onMouseReleased(PaintPane paintPane, double x, double y) {}

    @Override
    public void onMouseClicked(PaintPane paintPane, double x, double y) {}

    @Override
    public void onMouseDragged(PaintPane paintPane, double x, double y) {}
}
