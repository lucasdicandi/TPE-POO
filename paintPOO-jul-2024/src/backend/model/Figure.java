package backend.model;


import javafx.scene.paint.Color;

public abstract class Figure {
    private final Point startPoint;
    private final Point endPoint;
    private Color color;

    private Color secondaryColor;

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    private ShadowType shadowType = ShadowType.NONE;


    public Figure(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public abstract void draw(double diffX, double  diffY);

    public abstract boolean containsPoint(Point point);

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }


    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
       return color;
    }

    public abstract Figure clone();

    public abstract Figure[] divide();

    public void setShadowType(ShadowType newShadowType){
        this.shadowType = newShadowType;
    }

    public ShadowType getShadowType(){
        return shadowType;
    }

    public abstract void moveToCenter(double centerX, double centerY);
}
