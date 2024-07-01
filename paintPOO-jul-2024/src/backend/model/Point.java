package backend.model;

public class  Point {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double new_x) {
        this.x = new_x;
    }

    public void setY(double new_y){
        this.y = new_y;
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

    @Override
    public Point clone() {
        return new Point(this.x, this.y);
    }

}
