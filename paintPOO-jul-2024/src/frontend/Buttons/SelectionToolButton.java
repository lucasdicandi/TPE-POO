package frontend.Buttons;

import backend.model.Figure;
import backend.model.Point;
import frontend.PaintPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SelectionToolButton extends ToolButton {

    private double initialX, initialY;

    public SelectionToolButton() {
        super("Seleccionar");
    }

    @Override
    public void onMousePressed(PaintPane paintPane, double x, double y) {
        initialX = x;
        initialY = y;
        paintPane.setSelectedFigure(null);

        Point point = new Point(x, y);
        Figure f = paintPane.findFigureAtPoint(point);
        paintPane.setSelectedFigure(f);

        for (Figure figure : paintPane.getCanvasState().figures()) {
            if (figure.containsPoint(point)){
                paintPane.setSelectedFigure(figure);
                break;
            }
        }
    }

    @Override
    public void onMouseDragged(PaintPane paintPane, double x, double y) {
        Figure selectedFigure = paintPane.getSelectedFigure();
        if (selectedFigure != null) {
            double deltaX = x - initialX ;
            double deltaY = y - initialY ;
            selectedFigure.draw(deltaX, deltaY);
            initialX = x;
            initialY = y;
            paintPane.redrawCanvas();
        }
    }

    @Override
    public void onMouseClicked(PaintPane paintPane, double x, double y) {
        Point point = new Point(x, y);
        Figure figure = paintPane.findFigureAtPoint(point);
        paintPane.setSelectedFigure(figure);
        paintPane.getStatusPane().updateStatus(figure != null ? "Selected: " + figure : "No figure found");
        paintPane.redrawCanvas();
    }

    @Override
    public void onMouseReleased(PaintPane paintPane, double x, double y) {
        Figure f = paintPane.findFigureAtPoint(new Point(x, y));
        paintPane.setSelectedFigure(f);
    }
}
