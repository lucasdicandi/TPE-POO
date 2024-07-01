package frontend.Renders;

import backend.model.Circle;
import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public class CircleRenderer extends FigureRenderer {
    @Override
    public void render(Figure figure, GraphicsContext gc) {
        if (figure instanceof Circle circle) {
            double diameter = circle.getRadius() * 2;
            gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
            gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
        }
    }
}
