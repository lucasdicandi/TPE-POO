package backend.model;

import javafx.scene.canvas.GraphicsContext;

public class Square extends Rectangle{

    public Square(Point topLeft, double size) {
        super(topLeft, new Point(topLeft.getX() + size, topLeft.getY() + size));
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", super.getTopLeft(), super.getBottomRight());
    }

    @Override
    public void redraw(GraphicsContext gc) {
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }

    @Override
    public boolean containsPoint(Point point) {
        return point.getX() >= getTopLeft().getX() && point.getX() <= getBottomRight().getX()
                && point.getY() >= getTopLeft().getY() && point.getY() <= getBottomRight().getY();
    }

}
