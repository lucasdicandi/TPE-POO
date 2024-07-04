package frontend.Renders;


import backend.model.Ellipse;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public class EllipseRenderer extends FigureRenderer {
    @Override
    public void render(Figure figure, GraphicsContext gc) {
        if (figure instanceof Ellipse ellipse) {
            gc.strokeOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
            gc.fillOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2),
                    ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
        }
    }
}
