package frontend.Renders;

import backend.model.Rectangle;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

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
}
