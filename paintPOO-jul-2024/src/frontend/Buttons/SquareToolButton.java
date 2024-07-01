package frontend.Buttons;

import backend.model.Figure;
import backend.model.Point;
import backend.model.Rectangle;
import backend.model.Square;
import frontend.PaintPane;
import frontend.Renders.SquareRenderer;

public class SquareToolButton extends ToolButton {
    SquareRenderer squareToPlane = new SquareRenderer();
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
        Figure figure = new Square(startPoint, endPoint);
        if (startPoint != null && endPoint.getX() >= startPoint.getX() && endPoint.getY() >= startPoint.getY()) {
            paintPane.addFigure(figure);
            paintPane.setStartPoint(null);
            squareToPlane.render(figure, paintPane.getGc());
        }
    }
}

