package frontend.Renders;

import backend.model.Rectangle;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;


;

public class RectangleRenderer extends FigureRenderer {

    @Override
    public void render(Figure figure, GraphicsContext gc) {
        if (figure instanceof Rectangle rectangle) {
            gc.fillRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
                    Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
            gc.strokeRect(rectangle.getTopLeft().getX(), rectangle.getTopLeft().getY(),
                    Math.abs(rectangle.getTopLeft().getX() - rectangle.getBottomRight().getX()), Math.abs(rectangle.getTopLeft().getY() - rectangle.getBottomRight().getY()));
        }
    }

    @Override
    public Paint getColorGradiant(Figure figure) {
        return new LinearGradient(0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, figure.getColor()),
                new Stop(1, figure.getSecondaryColor()));
    }
}
