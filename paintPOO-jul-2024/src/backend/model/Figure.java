package backend.model;


import javafx.scene.paint.Color;

public abstract class Figure {
    private final Point startPoint;
    private final Point endPoint;
    private Color color;
    private Color secondaryColor;
    private ShadowType shadowType = ShadowType.NONE;
    private LineType lineType = LineType.NORMAL;
    private static final double DEFAULT_VALUE_LENGHT = 1;
    private double LineWidth = DEFAULT_VALUE_LENGHT;
    private int layer = 1;

    private boolean showable = true;

    public boolean isShowable() {
        return showable;
    }

    public void setShow(boolean show) {
        this.showable = show;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
    public int getLayer(){
        return this.layer;
    }

    public double getLineWidth() {
        return LineWidth;
    }

    public void setLineWidth(double LineWidth) {
        this.LineWidth = LineWidth;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }


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
