package backend.model;


import javafx.scene.paint.Color;

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
    public void draw(double diffX, double diffY) {
        centerPoint.setX(getCenterPoint().getX() + diffX);
        centerPoint.setY(getCenterPoint().getY() + diffY);
        getStartPoint().setX(getStartPoint().getX() + diffX);
        getStartPoint().setY(getStartPoint().getY() + diffY);
        getEndPoint().setX(getEndPoint().getX() + diffX);
        getEndPoint().setY(getEndPoint().getY() + diffY);
    }


    @Override
    public boolean containsPoint(Point point) {
        double normalizedX = Math.pow(point.getX() - centerPoint.getX(), 2) / Math.pow(sMayorAxis / 2, 2);
        double normalizedY = Math.pow(point.getY() - centerPoint.getY(), 2) / Math.pow(sMinorAxis / 2, 2);
        return (normalizedX + normalizedY) <= 1.0;
    }

    @Override
    public Ellipse clone() {
        Point clonedStartPoint = new Point(getStartPoint().getX(), getStartPoint().getY());
        Point clonedEndPoint = new Point(getEndPoint().getX(), getEndPoint().getY());
        Ellipse clonedEllipse = new Ellipse(clonedStartPoint, clonedEndPoint);
        clonedEllipse.setsMayorAxis(this.sMayorAxis);
        clonedEllipse.setsMinorAxis(this.sMinorAxis);
        clonedEllipse.setShadowType(this.getShadowType());
        return clonedEllipse;
    }

    @Override
    public Figure[] divide() {
        double halfMajorAxis = sMayorAxis / 2;

        Point newCenter1 = new Point(centerPoint.getX() - halfMajorAxis / 2, centerPoint.getY());
        Point newCenter2 = new Point(centerPoint.getX() + halfMajorAxis / 2, centerPoint.getY());

        Ellipse ellipse1 = new Ellipse(
                new Point(newCenter1.getX() - halfMajorAxis / 2, centerPoint.getY() - sMinorAxis / 2),
                new Point(newCenter1.getX() + halfMajorAxis / 2, centerPoint.getY() + sMinorAxis / 2)
        );
        Ellipse ellipse2 = new Ellipse(
                new Point(newCenter2.getX() - halfMajorAxis / 2, centerPoint.getY() - sMinorAxis / 2),
                new Point(newCenter2.getX() + halfMajorAxis / 2, centerPoint.getY() + sMinorAxis / 2)
        );

        ellipse1.setsMayorAxis(halfMajorAxis);
        ellipse1.setsMinorAxis(sMinorAxis/2);
        ellipse2.setsMayorAxis(halfMajorAxis);
        ellipse2.setsMinorAxis(sMinorAxis/2);

        ellipse1.setShadowType(this.getShadowType());
        ellipse2.setShadowType(this.getShadowType());

        return new Figure[] { ellipse1, ellipse2 };
    }

    @Override
    public void moveToCenter(double centerX, double centerY) {
        centerPoint.setX(centerX);
        centerPoint.setY(centerY);
        getStartPoint().setX(centerX - sMayorAxis / 2);
        getStartPoint().setY(centerY - sMinorAxis / 2);
        getEndPoint().setX(centerX + sMayorAxis / 2);
        getEndPoint().setY(centerY + sMinorAxis / 2);
    }
}
