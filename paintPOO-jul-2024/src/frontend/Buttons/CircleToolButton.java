package frontend.Buttons;

import backend.model.Circle;
import backend.model.Figure;
import backend.model.Point;
import frontend.PaintPane;

public class CircleToolButton extends ToolButton {
    public CircleToolButton() {
        super("Círculo");
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
            Figure newFigure = new Circle(startPoint, endPoint);
            paintPane.addFigure(newFigure);
            paintPane.setStartPoint(null);
            paintPane.redrawCanvas();
        }
    }
}

