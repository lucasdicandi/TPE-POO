package frontend.Buttons.ToolButtons;

import backend.model.Ellipse;
import backend.model.Figure;
import backend.model.Point;
import frontend.PaintPane;
import frontend.Renders.EllipseRenderer;

public class EllipseToolButton extends ToolButton {

    EllipseRenderer ellipseToPlain = new EllipseRenderer();
    public EllipseToolButton() {
        super("Elipse");
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
        Figure figure = new Ellipse(startPoint, endPoint);
        if (startPoint != null && endPoint.getX() >= startPoint.getX() && endPoint.getY() >= startPoint.getY()) {
            paintPane.addFigure(figure);
            paintPane.setStartPoint(null);
            ellipseToPlain.render(figure, paintPane.getGc());
            paintPane.redrawCanvas();
        }
    }
}

