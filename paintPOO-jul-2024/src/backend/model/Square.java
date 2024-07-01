package backend.model;


public class Square extends Rectangle{

    public Square(Point startPoint, Point endPoint) {
        super(startPoint, new Point(startPoint.getX() + Math.abs(endPoint.getX() - startPoint.getX()), startPoint.getY() + Math.abs(endPoint.getX() - startPoint.getX())));
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", super.getTopLeft(), super.getBottomRight());
    }

    @Override
    public boolean containsPoint(Point point) {
        return point.getX() >= getTopLeft().getX() && point.getX() <= getBottomRight().getX()
                && point.getY() >= getTopLeft().getY() && point.getY() <= getBottomRight().getY();
    }


}
