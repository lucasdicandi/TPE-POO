package frontend.Buttons.ToolButtons;

import backend.model.*;
import frontend.PaintPane;
import frontend.Renders.EllipseRenderer;

public class CircleToolButton extends ToolButton {

    EllipseRenderer circleToPlain = new EllipseRenderer();
    public CircleToolButton() {
        super("Círculo");
//        this.setOnAction(event -> {
//            paintPane.setSelectedFigure(null);
//        });
    }

    @Override
    public void onMousePressed(PaintPane paintPane, double x, double y) {
        paintPane.setSelectedFigure(null);
        paintPane.setStartPoint(new Point(x, y));
        paintPane.redrawCanvas();
    }

    @Override
    public void onMouseReleased(PaintPane paintPane, double x, double y) {
        Point startPoint = paintPane.getStartPoint();
        Point endPoint = new Point(x, y);
        Figure figure = new Circle(startPoint, endPoint);
        if (startPoint != null && endPoint.getX() >= startPoint.getX() && endPoint.getY() >= startPoint.getY()) {
            paintPane.addFigure(figure);
            paintPane.setStartPoint(null);
            circleToPlain.render(figure, paintPane.getGc());
            paintPane.redrawCanvas();
        }
    }
}

