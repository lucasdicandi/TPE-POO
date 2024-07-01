package backend.model;



public class Ellipse extends Figure {

    private final Point centerPoint;
    private double sMayorAxis;
    private double sMinorAxis;

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

    public void setsMayorAxis(double sMayorAxis) {
        this.sMayorAxis = sMayorAxis;
    }

    public void setsMinorAxis(double sMinorAxis) {
        this.sMinorAxis = sMinorAxis;
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
    public boolean containsPoint(Point point) {
        double normalizedX = Math.pow(point.getX() - centerPoint.getX(), 2) / Math.pow(sMayorAxis / 2, 2);
        double normalizedY = Math.pow(point.getY() - centerPoint.getY(), 2) / Math.pow(sMinorAxis / 2, 2);
        return (normalizedX + normalizedY) <= 1.0;
    }

    @Override
    public Ellipse clone() {
        // Create new points for the cloned ellipse
        Point clonedStartPoint = new Point(getStartPoint().getX(), getStartPoint().getY());
        Point clonedEndPoint = new Point(getEndPoint().getX(), getEndPoint().getY());
        // Create a new Ellipse with cloned points and the same axis lengths
        Ellipse clonedEllipse = new Ellipse(clonedStartPoint, clonedEndPoint);
        clonedEllipse.setsMayorAxis(this.sMayorAxis);
        clonedEllipse.setsMinorAxis(this.sMinorAxis);
        return clonedEllipse;
    }
}
