package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure {
    private Point startPoint;
    private Point endPoint;
    private Color color;


    public Figure(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    public abstract double area();

    public abstract double perimeter();

    public abstract void draw(double diffX, double  diffY);

    public abstract void redraw(GraphicsContext gc);

    public abstract boolean containsPoint(Point point);

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void move(double deltaX, double deltaY) {
        startPoint.setX(startPoint.getX() + deltaX);
        startPoint.setY(startPoint.getY() + deltaY);
        endPoint.setX(endPoint.getX() + deltaX);
        endPoint.setY(endPoint.getY() + deltaY);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }



}
