package backend.model;


import javafx.scene.paint.Color;

public abstract class Figure {
    private final Point startPoint;
    private final Point endPoint;
    private Color color;


    public Figure(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }
    public abstract double area();

    public abstract double perimeter();

    public abstract void draw(double diffX, double  diffY);

    public abstract boolean containsPoint(Point point);

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract Figure clone();

    public abstract Figure[] divide();
}
