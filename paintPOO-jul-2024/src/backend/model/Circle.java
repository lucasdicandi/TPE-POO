package backend.model;

public class Circle extends Ellipse {
    protected final double radius;

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, 2 * radius, 2 * radius);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, radius);
    }

    public double getRadius() {
        return radius;
    }

}
