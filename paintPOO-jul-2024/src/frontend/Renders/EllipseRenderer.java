package frontend.Renders;


import backend.model.Ellipse;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;


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

    @Override
    public Paint getColorGradiant(Figure figure) {
        return new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, figure.getColor()),
                new Stop(1, figure.getSecondaryColor()));
    }
}
