package frontend.Buttons;

import backend.model.Point;
import frontend.PaintPane;

public interface Button {
    void onMousePressed(PaintPane paintPane, double x, double y);
    void onMouseReleased(PaintPane paintPane, double x, double y);
    void onMouseClicked(PaintPane paintPane, double x, double y);
    void onMouseDragged(PaintPane paintPane, double x, double y);
}
