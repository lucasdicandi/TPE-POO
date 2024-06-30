package backend.model;

import javafx.scene.canvas.GraphicsContext;

public abstract class Figure {
    protected Point startPoint;
    protected Point endPoint;

    public Figure(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    public abstract double area();

    public abstract double perimeter();

    public abstract void draw(double diffX, double  diffY);

    public abstract void redraw(GraphicsContext gc);

    public abstract boolean containsPoint(Point point);
}
