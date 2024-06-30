package frontend.Buttons;

import backend.model.Figure;
import backend.model.Point;
import frontend.PaintPane;

public class SelectionToolButton extends ToolButton {
    public SelectionToolButton() {
        super("Seleccionar");
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
    public void onMouseDragged(PaintPane paintPane, double x, double y) {
        Figure selectedFigure = paintPane.getSelectedFigure();
        Point startPoint = paintPane.getStartPoint();

        if (selectedFigure != null && startPoint != null) {
            double diffX = (x - startPoint.getX()) / 100;
            double diffY = (y - startPoint.getY()) / 100;
            selectedFigure.draw(diffX, diffY);
            paintPane.redrawCanvas();
        }
    }

}

