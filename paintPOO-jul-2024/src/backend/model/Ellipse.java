package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse extends Figure {

    protected final Point centerPoint;
    protected double sMayorAxis;
    protected double sMinorAxis;

    public Ellipse(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        this.centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, Math.abs(endPoint.getY() + startPoint.getY()) / 2);
        this.sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
        this.sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }

    @Override
    public double area() {
        return Math.PI / 4 * sMayorAxis * sMinorAxis;
    }

    @Override
    public double perimeter() {
        return Math.PI / 2 * (sMayorAxis + sMinorAxis);
    }

    @Override
    public void draw(double diffX, double diffY) {
        centerPoint.setX(getCenterPoint().getX() + diffX);
        centerPoint.setY(getCenterPoint().getY() + diffY);
    }

    @Override
    public void redraw(GraphicsContext gc) {
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2),
                getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2),
                getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
    }

    @Override
    public boolean containsPoint(Point point) {
        double normalizedX = Math.pow(point.getX() - centerPoint.getX(), 2) / Math.pow(sMayorAxis / 2, 2);
        double normalizedY = Math.pow(point.getY() - centerPoint.getY(), 2) / Math.pow(sMinorAxis / 2, 2);
        return (normalizedX + normalizedY) <= 1.0;
    }
}
