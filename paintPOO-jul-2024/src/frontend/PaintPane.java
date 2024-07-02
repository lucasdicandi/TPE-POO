package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.Buttons.*;
import frontend.Renders.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
public class PaintPane extends BorderPane {
	private final CanvasState canvasState;
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();
	private final Color lineColor = Color.BLACK;
	private final Color defaultFillColor = Color.YELLOW;
	private Point startPoint;
	private Figure selectedFigure;
	private final StatusPane statusPane;
	private ToolButton currentTool;
	private final ToggleGroup toolsGroup = new ToggleGroup();
	private final ColorPicker fillColorPickerPrimary = new ColorPicker(defaultFillColor);
	private final ColorPicker fillColorPickerSecondary = new ColorPicker(defaultFillColor);
	private final Map<Class<? extends Figure>, FigureRenderer> rendererMap = new HashMap<>();
	private final ChoiceBox<ShadowType> shadowChoiceBox = new ChoiceBox<>();

	private final ChoiceBox<LineType> lineTypeChoiceBox = new ChoiceBox<>();
	private final Map<ShadowType, Color> shadowRendererMap = new HashMap<>();

	private final Slider lineWithSlider = new Slider(1, 10, 5);


	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		initializeTools();
		initializeUI();
		setupCanvasEvents();
		this.rendererMap.put(Circle.class, new CircleRenderer());
		this.rendererMap.put(Rectangle.class, new RectangleRenderer());
		this.rendererMap.put(Ellipse.class, new EllipseRenderer());
		this.rendererMap.put(Square.class, new SquareRenderer());
		shadowRendererMap.put(ShadowType.NONE, Color.TRANSPARENT);
		shadowRendererMap.put(ShadowType.SIMPLE, Color.GRAY);
		shadowRendererMap.put(ShadowType.SIMPLE_INVERSE, Color.GRAY);
	}

	private void initializeTools() {
		ToolButton[] toolsArr = {
				new SelectionToolButton(),
				new RectangleToolButton(),
				new CircleToolButton(),
				new SquareToolButton(),
				new EllipseToolButton(),
				new DivideToolButton(this),
				new DuplicateToolButton(this),
				new DeleteToolButton(this),
				new CenterToolButton(this)
		};

		for (ToolButton tool : toolsArr) {
			tool.setToggleGroup(toolsGroup);
			tool.setOnAction(event -> currentTool = tool);
		}
	}

	private void initializeUI() {
		shadowChoiceBox.getItems().addAll(ShadowType.values());
		shadowChoiceBox.setValue(ShadowType.NONE);
		shadowChoiceBox.setOnAction(event -> {
			if(selectedFigure != null) {
				selectedFigure.setShadowType(shadowChoiceBox.getValue());
				redrawCanvas();
			}
		});

		lineTypeChoiceBox.getItems().addAll(LineType.values());
		lineTypeChoiceBox.setValue(LineType.NORMAL);
		lineTypeChoiceBox.setOnAction(event -> {
			if(selectedFigure != null) {
				selectedFigure.setLineType(lineTypeChoiceBox.getValue());
				redrawCanvas();
			}
		});

		lineWithSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			if (selectedFigure != null) {
				selectedFigure.setLineWidth(newVal.doubleValue());
				redrawCanvas();
			}
		});

		fillColorPickerPrimary.setOnAction(event ->{
			if (selectedFigure != null) {
				selectedFigure.setColor(fillColorPickerPrimary.getValue());
				redrawCanvas();
			}
		});

		fillColorPickerSecondary.setOnAction(event ->{
			if (selectedFigure != null) {
				selectedFigure.setSecondaryColor(fillColorPickerSecondary.getValue());
				redrawCanvas();
			}
		});



		VBox buttonsBox = new VBox(10);
		for (var toggle : toolsGroup.getToggles()) {
			buttonsBox.getChildren().add((Node) toggle);
		}
		buttonsBox.getChildren().add(fillColorPickerPrimary);
		buttonsBox.getChildren().add(fillColorPickerSecondary);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);

		buttonsBox.getChildren().add(shadowChoiceBox);
		buttonsBox.getChildren().add(lineTypeChoiceBox);

		setLeft(buttonsBox);
		setRight(canvas);

		VBox sidebar = new VBox();
		buttonsBox.setSpacing(10);

		lineWithSlider.setShowTickMarks(true);
		lineWithSlider.setShowTickLabels(true);

		buttonsBox.getChildren().addAll(lineWithSlider);

	}

	private void setupCanvasEvents() {
				canvas.setOnMousePressed(event -> {
			if (currentTool != null) {
				currentTool.onMousePressed(this, event.getX(), event.getY());
			}
		});

		canvas.setOnMouseReleased(event -> {
			if (currentTool != null) {
				currentTool.onMouseReleased(this, event.getX(), event.getY());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if (currentTool != null) {
				currentTool.onMouseClicked(this, event.getX(), event.getY());
			}

		});

		canvas.setOnMouseDragged(event -> {
			if (currentTool != null) {
				currentTool.onMouseDragged(this, event.getX(), event.getY());
			}
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			Figure hoveredFigure = findFigureAtPoint(eventPoint);
			statusPane.updateStatus(hoveredFigure != null ? hoveredFigure.toString() : eventPoint.toString());
		});
	}

	public void addFigure(Figure figure) {
		figure.setColor(fillColorPickerPrimary.getValue());
		figure.setSecondaryColor(fillColorPickerSecondary.getValue());

		shadowRendererMap.put(ShadowType.COLORED, fillColorPickerPrimary.getValue().darker());
		shadowRendererMap.put(ShadowType.COLORED_INVERSE, fillColorPickerPrimary.getValue().darker());

		shadowChoiceBox.setValue(ShadowType.NONE);

		canvasState.addFigure(figure);
		redrawCanvas();
	}

	public void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Figure figure : canvasState.figures()) {
			gc.setLineWidth(figure.getLineWidth());

			Color shadowColor = shadowRendererMap.get(figure.getShadowType());
			gc.setStroke(Color.TRANSPARENT);
			gc.setFill(shadowColor);
			FigureRenderer renderer = rendererMap.get(figure.getClass());
			renderer.renderShadow(figure, gc, shadowColor);

			gc.setLineDashes(figure.getLineType().getDashes());

			RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
					CycleMethod.NO_CYCLE,
					new Stop(0, figure.getColor()),
					new Stop(1, figure.getSecondaryColor()));

			gc.setStroke(figure == selectedFigure ? Color.RED : lineColor);
			gc.setFill(radialGradient);
			renderer.render(figure, gc);


		}
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public Figure findFigureAtPoint(Point point) {
		for (Figure figure : canvasState.figures()) {
			if (figure.containsPoint(point)) {
				return figure;
			}
		}
		return null;
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

	public void removeFigure(Figure figure) {
		canvasState.deleteFigure(figure);
		redrawCanvas();
	}

	public CanvasState getCanvasState() {
		return canvasState;
	}

	public double getCanvasWidth() {
		return canvas.getWidth();
	}

	public double getCanvasHeight() {
		return canvas.getHeight();
	}


}
