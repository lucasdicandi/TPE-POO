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


    @Override
    public void draw(double diffX, double diffY) {
        if(isShowable()) {
            topLeft.setX(getTopLeft().getX() + diffX);
            bottomRight.setX(getBottomRight().getX() + diffX);
            topLeft.setY(getTopLeft().getY() + diffY);
            bottomRight.setY(getBottomRight().getY() + diffY);
        }
    }


    @Override
    public boolean containsPoint(Point point) {
        return point.getX() > topLeft.getX() && point.getX() < bottomRight.getX()
                && point.getY() > topLeft.getY() && point.getY() < bottomRight.getY();
    }


    @Override
    public Rectangle clone() {
        Point clonedTopLeft = new Point(topLeft.getX(), topLeft.getY());
        Point clonedBottomRight = new Point(bottomRight.getX(), bottomRight.getY());
        Rectangle toReturn = new Rectangle(clonedTopLeft, clonedBottomRight);
        cloneProperties(toReturn);
        return toReturn;
    }


    @Override
    public Figure[] divide() {
        double halfWidth = Math.abs(bottomRight.getX() - topLeft.getX()) / 2;
        double height = Math.abs(bottomRight.getY() - topLeft.getY());

        Point newTopLeft1 = new Point(topLeft.getX(), topLeft.getY() + height / 4);
        Point newBottomRight1 = new Point(topLeft.getX() + halfWidth, bottomRight.getY() - height / 4);

        Point newTopLeft2 = new Point(topLeft.getX() + halfWidth, topLeft.getY() + height / 4);
        Point newBottomRight2 = new Point(bottomRight.getX(), bottomRight.getY() - height / 4);

        Rectangle rect1 = new Rectangle(newTopLeft1, newBottomRight1);
        Rectangle rect2 = new Rectangle(newTopLeft2, newBottomRight2);

        cloneProperties(rect1);
        cloneProperties(rect2);

        return new Figure[] { rect1, rect2 };
    }

    @Override
    public void moveToCenter(double centerX, double centerY) {
        double width = bottomRight.getX() - topLeft.getX();
        double height = bottomRight.getY() - topLeft.getY();
        double newTopLeftX = centerX - width / 2;
        double newTopLeftY = centerY - height / 2;

        topLeft.setX(newTopLeftX);
        topLeft.setY(newTopLeftY);
        bottomRight.setX(newTopLeftX + width);
        bottomRight.setY(newTopLeftY + height);
    }
}
