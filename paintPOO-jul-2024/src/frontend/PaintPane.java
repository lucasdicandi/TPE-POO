package frontend;
import backend.CanvasState;
import backend.model.*;
import frontend.Buttons.ChoiceBox.LineTypeChoiceBox;
import frontend.Buttons.ChoiceBox.ShadowChoiceBox;
import frontend.Buttons.ColorPicker.FillColorPickerPrimary;
import frontend.Buttons.ColorPicker.FillColorPickerSecondary;
import frontend.Buttons.RadioButton.HideLayerRadioButton;
import frontend.Buttons.RadioButton.ShowLayerRadioButton;
import frontend.Buttons.Slider.LineWithSliderButton;
import frontend.Buttons.ToolButtons.*;
import frontend.Renders.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.*;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.scene.paint.*;

public class PaintPane extends BorderPane {
	private final CanvasState canvasState;
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();
	private final Color lineColor = Color.BLACK, defaultFillColor = Color.YELLOW;
	private Point startPoint;
	private Figure selectedFigure;
	private final StatusPane statusPane;
	private ToolButton currentTool;
	private final ToggleGroup toolsGroup = new ToggleGroup();
	private final FillColorPickerPrimary fillColorPickerPrimary = new FillColorPickerPrimary(this, defaultFillColor);
	private final FillColorPickerSecondary fillColorPickerSecondary = new FillColorPickerSecondary(this, defaultFillColor);
	private final Map<Class<? extends Figure>, FigureRenderer> rendererMap = new HashMap<>();
	private final Map<String, Integer> layersMap = new TreeMap<>();
	private final Map<ShadowType, Color> shadowRendererMap = new HashMap<>();
	private final ShadowChoiceBox shadowChoiceBox = new ShadowChoiceBox(this);
	private final LineTypeChoiceBox lineTypeChoiceBox = new LineTypeChoiceBox(this);
	private final ChoiceBox<String> layerChoiceBox = new ChoiceBox<>();
	private final ShowLayerRadioButton showLayerButton = new ShowLayerRadioButton(this);
	private final HideLayerRadioButton hideLayerButton = new HideLayerRadioButton(this);
	private final LineWithSliderButton lineWithSlider = new LineWithSliderButton(1, 10, 5, this);
	private static final int INITIAL_LAYER = 4;
	private int nextLayerNumber = INITIAL_LAYER;

	private final AddLayerToolButton addLayerButton = new AddLayerToolButton(this);
	private final DeleteLayerToolButton deleteLayerButton = new DeleteLayerToolButton(this);

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		initializeTools();
		initializeUI();
		setupCanvasEvents();
		setUpPropertiesMaps();

	}

	private void initializeUI() {
		VBox buttonsBox = new VBox(10);
		renderChoiceButtons(buttonsBox);
		HBox topBar = new HBox(10);
		renderHBox(topBar);
		setTop(topBar);
		setLeft(buttonsBox);
		setRight(canvas);

	}
	private void initializeTools() {
		ToolButton[] toolsArr = {
				new SelectionToolButton(),
				new RectangleToolButton(),
				new CircleToolButton(),
				new SquareToolButton(),
				new EllipseToolButton(),
				new DeleteToolButton(this),
		};

		for (ToolButton tool : toolsArr) {
			tool.setToggleGroup(toolsGroup);
			tool.setOnAction(event -> currentTool = tool);
		}
		
	}

	public void setUpPropertiesMaps(){
		this.rendererMap.put(Circle.class, new CircleRenderer());
		this.rendererMap.put(Rectangle.class, new RectangleRenderer());
		this.rendererMap.put(Ellipse.class, new EllipseRenderer());
		this.rendererMap.put(Square.class, new SquareRenderer());
		shadowRendererMap.put(ShadowType.NONE, Color.TRANSPARENT);
		shadowRendererMap.put(ShadowType.SIMPLE, Color.GRAY);
		shadowRendererMap.put(ShadowType.SIMPLE_INVERSE, Color.GRAY);
	}


	public void renderChoiceButtons(VBox buttonsBox){
		for (var toggle : toolsGroup.getToggles()) {
			buttonsBox.getChildren().add((Node) toggle);
		}
		buttonsBox.getChildren().addAll(new Label("Sombra:"), shadowChoiceBox);
		buttonsBox.getChildren().addAll(new Label("Relleno:"), fillColorPickerPrimary, fillColorPickerSecondary);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		setLeft(buttonsBox);
		setRight(canvas);
		buttonsBox.setSpacing(10);
		lineWithSlider.setShowTickMarks(true);
		lineWithSlider.setShowTickLabels(true);
		buttonsBox.getChildren().addAll(new Label("Borde:"), lineWithSlider, lineTypeChoiceBox);
		buttonsBox.getChildren().addAll(new Label("Acciones:"), new DuplicateToolButton(this), new DivideToolButton(this), new CenterToolButton(this));
	}

	private void renderHBox(HBox topBar){
		topBar.setPadding(new Insets(5));
		topBar.setAlignment(Pos.CENTER);
		topBar.setStyle("-fx-background-color: #999");

		layersMap.put("Capa 1", 1);
		layersMap.put("Capa 2", 2);
		layersMap.put("Capa 3", 3);


		layerChoiceBox.setItems(FXCollections.observableArrayList(layersMap.keySet()));

		layerChoiceBox.setValue("Capa 1");

		layerChoiceBox.setOnAction(event -> {
			if(selectedFigure != null) {
				selectedFigure.setLayer(layersMap.getOrDefault(layerChoiceBox.getValue(), 1));
				redrawCanvas();
			}
		});

		ToggleGroup visibilityGroup = new ToggleGroup();

		showLayerButton.setToggleGroup(visibilityGroup);
		hideLayerButton.setToggleGroup(visibilityGroup);
		showLayerButton.setSelected(true);

		visibilityGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
			if (newToggle != null) {
				RadioButton selectedButton = (RadioButton) newToggle;
				String selectedLayer = layerChoiceBox.getValue();
				redrawCanvas();
			}
		});

		topBar.getChildren().addAll(
				new Label("Capas:"),
				layerChoiceBox,
				showLayerButton,
				hideLayerButton,
				addLayerButton,
				deleteLayerButton
		);
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
		shadowChoiceBox.setValue(ShadowType.NONE);
		canvasState.addFigure(figure);
		layerChoiceBox.setValue("Capa 1");
		redrawCanvas();
	}

	public void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Figure figure : canvasState.figures()) {
			if(figure.isShowable()) {
				renderFigure(figure);
			}
		}
	}

	private void renderFigure(Figure figure){
		gc.setLineWidth(figure.getLineWidth());
		shadowRendererMap.put(ShadowType.COLORED, figure.getColor().darker());
		shadowRendererMap.put(ShadowType.COLORED_INVERSE, figure.getColor().darker());
		Color shadowColor = shadowRendererMap.get(figure.getShadowType());
		gc.setStroke(Color.TRANSPARENT);
		gc.setFill(shadowColor);
		FigureRenderer renderer = rendererMap.get(figure.getClass());
		renderer.renderShadow(figure, gc, shadowColor);
		gc.setLineDashes(figure.getLineType().getDashes());
		Paint paint = renderer.getColorGradiant(figure);
		gc.setStroke(figure == selectedFigure ? Color.RED : lineColor);
		gc.setFill(paint);
		renderer.render(figure, gc);
	}


	public Figure findFigureAtPoint(Point point) {
		for (Figure figure : canvasState.figures().reversed()) {
			if (figure.containsPoint(point)) {
				return figure;
			}
		}

		return null;
	}

	public void removeFigure(Figure figure) {
		canvasState.deleteFigure(figure);
		redrawCanvas();
	}

	//setters and getters

	public int getNextLayerNumber() {
		return nextLayerNumber;
	}

	public void incrementNextLayerNumber() {
		nextLayerNumber++;
	}

	public Map<String, Integer> getLayersMap() {
		return layersMap;
	}
	public ChoiceBox<String> getLayerChoiceBox() {
		return layerChoiceBox;
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
		return canvasState;
	}
	public double getCanvasWidth() {
		return canvas.getWidth();
	}
	public double getCanvasHeight() {
		return canvas.getHeight();
	}
	public ColorPicker getFillColorPickerPrimary() {
		return fillColorPickerPrimary;
	}
	public ColorPicker getFillColorPickerSecondary() {
		return fillColorPickerSecondary;
	}
	public ChoiceBox<ShadowType> getShadowChoiceBox() {
		return shadowChoiceBox;
	}
	public ChoiceBox<LineType> getLineTypeChoiceBox() {
		return lineTypeChoiceBox;
	}

	public Map<ShadowType, Color> getShadowRendererMap(){
		return shadowRendererMap;
	}

}
