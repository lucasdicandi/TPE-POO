package frontend;
import backend.CanvasState;
import backend.model.*;
import frontend.Buttons.ButtonBox;
import frontend.Buttons.ToolButtons.SelectionToolButton;
import frontend.Renders.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import java.util.*;
import javafx.scene.control.ChoiceBox;

public class PaintPane extends BorderPane {
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();
	private final Color lineColor = Color.BLACK;
	private Point startPoint;
	private Figure selectedFigure;
	private final StatusPane statusPane;
	private final Map<Class<? extends Figure>, FigureRenderer> rendererMap = new HashMap<>();
	private ButtonBox buttonBox;

	public void addButtonBox(ButtonBox buttonBox){
		this.buttonBox = buttonBox;
		setRight(canvas);
		setUpPropertiesMaps();
		buttonBox.initialize();
	}

	public PaintPane(StatusPane statusPane) {
		this.statusPane = statusPane;
	}

	public void setUpPropertiesMaps(){
		this.rendererMap.put(Circle.class, new CircleRenderer());
		this.rendererMap.put(Rectangle.class, new RectangleRenderer());
		this.rendererMap.put(Ellipse.class, new EllipseRenderer());
		this.rendererMap.put(Square.class, new SquareRenderer());
		if(buttonBox != null) {
			buttonBox.getShadowRendererMap().put(ShadowType.NONE, Color.TRANSPARENT);
			buttonBox.getShadowRendererMap().put(ShadowType.SIMPLE, Color.GRAY);
			buttonBox.getShadowRendererMap().put(ShadowType.SIMPLE_INVERSE, Color.GRAY);
		}
	}

	public void addFigure(Figure figure) {
		figure.setColor(buttonBox.getFillColorPickerPrimary().getValue());
		figure.setSecondaryColor(buttonBox.getFillColorPickerSecondary().getValue());
		buttonBox.getShadowChoiceBox().setValue(ShadowType.NONE);
		buttonBox.getCanvasState().addFigure(figure);
		buttonBox.getLayerChoiceBox().setValue("Capa 1");
		redrawCanvas();
	}

	public void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Figure figure : buttonBox.getCanvasState().figures()) {
			if(figure.isShowable()) {
				renderFigure(figure);
			}
		}
	}

	private void renderFigure(Figure figure){
		gc.setLineWidth(figure.getLineWidth());
		buttonBox.getShadowRendererMap().put(ShadowType.COLORED, figure.getColor().darker());
		buttonBox.getShadowRendererMap().put(ShadowType.COLORED_INVERSE, figure.getColor().darker());
		gc.setFill(buttonBox.getShadowRendererMap().get(figure.getShadowType()));
		gc.setStroke(Color.TRANSPARENT);
		rendererMap.get(figure.getClass()).renderShadow(figure, gc, buttonBox.getShadowRendererMap().get(figure.getShadowType()));
		gc.setLineDashes(figure.getLineType().getDashes());
		gc.setStroke(figure == selectedFigure ? Color.RED : lineColor);
		gc.setFill(rendererMap.get(figure.getClass()).getColorGradiant(figure));
		rendererMap.get(figure.getClass()).render(figure, gc);
	}

	public Figure findFigureAtPoint(Point point) {
		for (Figure figure : buttonBox.getCanvasState().figuresReversed()) {
			if (figure.containsPoint(point)) {
				return figure;
			}
		}

		return null;
	}

	public void removeFigure(Figure figure) {
		buttonBox.getCanvasState().deleteFigure(figure);
		redrawCanvas();
	}

	//setters and getters

	public int getNextLayerNumber() {
		return buttonBox.getNextLayerNumber();
	}
	public void incrementNextLayerNumber() {
		buttonBox.incrementNextLayerNumber();
	}
	public Map<String, Integer> getLayersMap() {
		return buttonBox.getLayersMap();
	}
	public ChoiceBox<String> getLayerChoiceBox() {
		return buttonBox.getLayerChoiceBox();
	}
	public GraphicsContext getGc() {
		return gc;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Figure getSelectedFigure() {
		return selectedFigure;
	}
	public void setSelectedFigure(Figure selectedFigure) {
		this.selectedFigure = selectedFigure;
	}
	public StatusPane getStatusPane() {
		return statusPane;
	}
	public CanvasState getCanvasState() {
		return buttonBox.getCanvasState();
	}
	public double getCanvasWidth() {
		return canvas.getWidth();
	}
	public double getCanvasHeight() {
		return canvas.getHeight();
	}
	public Map<ShadowType, Color> getShadowRendererMap(){
		return buttonBox.getShadowRendererMap();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public ButtonBox getButtonBox() {
		return buttonBox;
	}


}
