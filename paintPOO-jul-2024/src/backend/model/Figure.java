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
    private static final int DEFAULT_VALUE_LAYER = 1;
    private double LineWidth = DEFAULT_VALUE_LENGHT;
    private int layer = DEFAULT_VALUE_LAYER;


    private boolean showable = true;

    public Figure(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    protected void cloneProperties(Figure figure){
        figure.setShadowType(this.getShadowType());
        figure.setLineType(this.getLineType());
        figure.setLineWidth(this.getLineWidth());
        figure.setLayer(this.getLayer());
        figure.setColor(this.getColor());
        figure.setSecondaryColor(this.getSecondaryColor());
    }

    //functionalities

    public abstract Figure clone();

    public abstract Figure[] divide();

    public abstract void moveToCenter(double centerX, double centerY);

    public abstract boolean containsPoint(Point point);

    public abstract void draw(double diffX, double  diffY);


    //setters and getters

    public void setShow(boolean show) {
        this.showable = show;
    }

    public boolean isShowable() {
        return showable;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getLayer(){
        return this.layer;
    }

    public void setLineWidth(double LineWidth) {
        this.LineWidth = LineWidth;
    }

    public double getLineWidth() {
        return LineWidth;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public LineType getLineType() {
        return lineType;
    }

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

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setShadowType(ShadowType newShadowType){
        this.shadowType = newShadowType;
    }

    public ShadowType getShadowType(){
        return shadowType;
    }


}
