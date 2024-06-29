package backend.model;

import javafx.scene.canvas.GraphicsContext;

public interface Figure {
    double area();

    double perimeter();

    void draw(double diffX, double  diffY);

    void redraw(GraphicsContext gc);

    boolean containsPoint(Point point);
}
