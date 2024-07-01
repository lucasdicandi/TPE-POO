package backend.model;


public class Rectangle extends Figure {

    private final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
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


    @Override
    public boolean containsPoint(Point point) {
        return point.getX() > topLeft.getX() && point.getX() < bottomRight.getX()
                && point.getY() > topLeft.getY() && point.getY() < bottomRight.getY();
    }


    @Override
    public Figure clone() {
        Point clonedTopLeft = new Point(topLeft.getX(), topLeft.getY());
        Point clonedBottomRight = new Point(bottomRight.getX(), bottomRight.getY());
        return new Rectangle(clonedTopLeft, clonedBottomRight);
    }


    @Override
    public Figure[] divide() {
        double halfWidth = Math.abs(bottomRight.getX() - topLeft.getX()) / 2;
        double halfHeight = Math.abs(bottomRight.getY() - topLeft.getY()) / 2;

        Point newBottomRight1 = new Point(topLeft.getX() + halfWidth, topLeft.getY() + halfHeight);
        Point newTopLeft2 = new Point(topLeft.getX() + halfWidth, topLeft.getY());
        Point newBottomRight2 = new Point(bottomRight.getX(), topLeft.getY() + halfHeight);

        Rectangle rect1 = new Rectangle(topLeft, newBottomRight1);
        Rectangle rect2 = new Rectangle(newTopLeft2, newBottomRight2);

        return new Figure[] { rect1, rect2 };
    }
}
