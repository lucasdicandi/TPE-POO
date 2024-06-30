package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Circle extends Ellipse {
    protected final double radius;


    public Circle(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        this.radius = Math.abs(endPoint.getX() - startPoint.getX()) / 2;
        this.sMayorAxis = radius * 2;
        this.sMinorAxis = radius * 2;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void redraw(GraphicsContext gc) {
        double diameter = getRadius() * 2;
        gc.fillOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), diameter, diameter);
        gc.strokeOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), diameter, diameter);
    }

    @Override
    public boolean containsPoint(Point point) {
        double distance = Math.sqrt(Math.pow(centerPoint.getX() - point.getX(), 2) +
                Math.pow(centerPoint.getY() - point.getY(), 2));
        return distance < radius;
    }

}
