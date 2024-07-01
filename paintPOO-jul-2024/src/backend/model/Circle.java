package backend.model;



public class Circle extends Ellipse {
    private final double radius;

    public Circle(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        this.radius = Math.abs(endPoint.getX() - startPoint.getX()) / 2;
        super.setsMayorAxis(radius * 2);
        super.setsMinorAxis(radius * 2);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", super.getCenterPoint(), radius);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean containsPoint(Point point) {
        double distance = Math.sqrt(Math.pow(super.getCenterPoint().getX() - point.getX(), 2) +
                Math.pow(super.getCenterPoint().getY() - point.getY(), 2));
        return distance < radius;
    }

}
