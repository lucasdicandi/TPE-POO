package backend.model;

public class Rectangle implements Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    public double base() {
        return Math.abs(topLeft.getX() - bottomRight.getX());
    }

    public double height() {
        return Math.abs(topLeft.getY() - bottomRight.getY());
    }

    @Override
    public double area() {
        return base() * height();
    }

    @Override
    public double perimeter() {
        return (base() + height()) * 2;
    }

    @Override
    public void draw(double diffX, double diffY) {
        topLeft.setX(getTopLeft().getX() + diffX);
        bottomRight.setX(getBottomRight().getX() + diffX);
        topLeft.setY(getTopLeft().getY() + diffY);
        bottomRight.setY(getBottomRight().getY() + diffY);
    }
}
