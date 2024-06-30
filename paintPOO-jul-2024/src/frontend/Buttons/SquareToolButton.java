package frontend.Buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Square;
import frontend.PaintPane;

public class SquareToolButton extends ToolButton {
    public SquareToolButton() {
        super("Cuadrado");
    }

    @Override
    public void onMousePressed(PaintPane paintPane, double x, double y) {
        paintPane.setStartPoint(new Point(x, y));
    }

    @Override
    public void onMouseReleased(PaintPane paintPane, double x, double y) {
        Point startPoint = paintPane.getStartPoint();
        Point endPoint = new Point(x, y);
        if (startPoint != null && endPoint.getX() >= startPoint.getX() && endPoint.getY() >= startPoint.getY()) {
            Figure newFigure = new Square(startPoint, endPoint);
            paintPane.addFigure(newFigure);
            paintPane.setStartPoint(null);
            paintPane.redrawCanvas();
        }
    }
}

