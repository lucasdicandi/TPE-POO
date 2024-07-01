package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.Buttons.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class PaintPane extends BorderPane {
	private CanvasState canvasState;
	private Canvas canvas = new Canvas(800, 600);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private Color lineColor = Color.BLACK;
	private Color defaultFillColor = Color.YELLOW;
	private Point startPoint;
	private Figure selectedFigure;
	private StatusPane statusPane;
	private ToolButton currentTool;
	private ToggleGroup toolsGroup = new ToggleGroup();
	private ColorPicker fillColorPicker = new ColorPicker(defaultFillColor);


	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		initializeTools();
		initializeUI();
		setupCanvasEvents();
	}

	private void initializeTools() {
		ToolButton[] toolsArr = {
				new SelectionToolButton(),
				new RectangleToolButton(),
				new CircleToolButton(),
				new SquareToolButton(),
				new EllipseToolButton(),
				new DeleteToolButton(this)
		};

		for (ToolButton tool : toolsArr) {
			tool.setToggleGroup(toolsGroup);
			tool.setOnAction(event -> currentTool = tool);
		}
	}

	private void initializeUI() {
		VBox buttonsBox = new VBox(10);
		for (var toggle : toolsGroup.getToggles()) {
			buttonsBox.getChildren().add((Node) toggle);
		}
		buttonsBox.getChildren().add(fillColorPicker);
		buttonsBox.setPadding(new Insets(5));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		setLeft(buttonsBox);
		setRight(canvas);
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
		figure.setColor(fillColorPicker.getValue());
		canvasState.addFigure(figure);
		redrawCanvas();
	}

	public void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Figure figure : canvasState.figures()) {
			gc.setStroke(figure == selectedFigure ? Color.RED : lineColor);
			gc.setFill(figure.getColor());
			figure.redraw(gc);
		}
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
}
