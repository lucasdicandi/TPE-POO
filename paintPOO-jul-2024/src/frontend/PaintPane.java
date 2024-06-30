package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.Buttons.*;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

//NO BUTTON IMPLEMENTATION
//
//public class PaintPane extends BorderPane {
//
//	// BackEnd
//	CanvasState canvasState;
//
//	// Canvas y relacionados
//	Canvas canvas = new Canvas(800, 600);
//	GraphicsContext gc = canvas.getGraphicsContext2D();
//	Color lineColor = Color.BLACK;
//	Color defaultFillColor = Color.YELLOW;
//
//
//	// Botones Barra Izquierda
//	ToggleButton selectionButton = new ToggleButton("Seleccionar");
//	ToggleButton rectangleButton = new ToggleButton("Rectángulo");
//	ToggleButton circleButton = new ToggleButton("Círculo");
//	ToggleButton squareButton = new ToggleButton("Cuadrado");
//	ToggleButton ellipseButton = new ToggleButton("Elipse");
//	ToggleButton deleteButton = new ToggleButton("Borrar");
//
//
//	// Selector de color de relleno
//	ColorPicker fillColorPicker = new ColorPicker(defaultFillColor);
//
//	// Dibujar una figura
//	Point startPoint;
//
//	// Seleccionar una figura
//	Figure selectedFigure;
//
//	// StatusBar
//	StatusPane statusPane;
//
//	// Colores de relleno de cada figura
//	Map<Figure, Color> figureColorMap = new HashMap<>();
//
//	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
//		this.canvasState = canvasState;
//		this.statusPane = statusPane;
//		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
//		ToggleGroup tools = new ToggleGroup();
//		for (ToggleButton tool : toolsArr) {
//			tool.setMinWidth(90);
//			tool.setToggleGroup(tools);
//			tool.setCursor(Cursor.HAND);
//		}
//
//		VBox buttonsBox = new VBox(10);
//		buttonsBox.getChildren().addAll(toolsArr);
//		buttonsBox.getChildren().add(fillColorPicker);
//		buttonsBox.setPadding(new Insets(5));
//		buttonsBox.setStyle("-fx-background-color: #999");
//		buttonsBox.setPrefWidth(100);
//		gc.setLineWidth(1);
//
//		canvas.setOnMousePressed(event -> {
//			startPoint = new Point(event.getX(), event.getY());
//		});
//
//		canvas.setOnMouseReleased(event -> {
//			Point endPoint = new Point(event.getX(), event.getY());
//			if(startPoint == null) {
//				return ;
//			}
//			if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
//				return ;
//			}
//			Figure newFigure = null;
//			if (rectangleButton.isSelected()) {
//				newFigure = new Rectangle(startPoint, endPoint);
//			} else if (circleButton.isSelected()) {
//				newFigure = new Circle(startPoint, endPoint);
//			} else if (squareButton.isSelected()) {
//				newFigure = new Square(startPoint, endPoint);
//			} else if (ellipseButton.isSelected()) {
//				newFigure = new Ellipse(startPoint, endPoint);
//			} else {
//				return ;
//			}
//
//			figureColorMap.put(newFigure, fillColorPicker.getValue());
//			canvasState.addFigure(newFigure);
//			startPoint = null;
//			redrawCanvas();
//		});
//
//
//		canvas.setOnMouseMoved(event -> {
//			Point eventPoint = new Point(event.getX(), event.getY());
//			Figure hoveredFigure = findFigureAtPoint(eventPoint);
//
//			if (hoveredFigure != null) {
//				statusPane.updateStatus(hoveredFigure.toString());
//			} else {
//				statusPane.updateStatus(eventPoint.toString());
//			}
//		});
//
//		canvas.setOnMouseClicked(event -> {
//			if(selectionButton.isSelected()) {
//				Point eventPoint = new Point(event.getX(), event.getY());
//				boolean found = false;
//				StringBuilder label = new StringBuilder("Se seleccionó: ");
//				for (Figure figure : canvasState.figures()) {
//					if(figureBelongs(figure, eventPoint)) {
//						found = true;
//						selectedFigure = figure;
//						label.append(figure.toString());
//					}
//				}
//				if (found) {
//					statusPane.updateStatus(label.toString());
//				} else {
//					selectedFigure = null;
//					statusPane.updateStatus("Ninguna figura encontrada");
//				}
//				redrawCanvas();
//			}
//		});
//
//		canvas.setOnMouseClicked(event -> {
//			if (selectionButton.isSelected()) {
//				Point eventPoint = new Point(event.getX(), event.getY());
//				Figure clickedFigure = findFigureAtPoint(eventPoint);
//
//				if (clickedFigure != null) {
//					selectedFigure = clickedFigure;
//					statusPane.updateStatus("Se seleccionó: " + selectedFigure.toString());
//				} else {
//					selectedFigure = null;
//					statusPane.updateStatus("Ninguna figura encontrada");
//				}
//
//				redrawCanvas();
//			}
//		});
//
//
//		canvas.setOnMouseDragged(event -> {
//			if (selectionButton.isSelected() && selectedFigure != null) {
//				Point eventPoint = new Point(event.getX(), event.getY());
//				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
//				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
//
//				selectedFigure.draw(diffX, diffY);
//				redrawCanvas();
//			}
//		});
//
//		deleteButton.setOnAction(event -> {
//			if (selectedFigure != null) {
//				canvasState.deleteFigure(selectedFigure);
//				selectedFigure = null;
//				redrawCanvas();
//			}
//		});
//
//		setLeft(buttonsBox);
//		setRight(canvas);
//	}
//
//	private void redrawCanvas() {
//		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//		for (Figure figure : canvasState.figures()) {
//			if (figure == selectedFigure) {
//				gc.setStroke(Color.RED);
//			} else {
//				gc.setStroke(lineColor);
//			}
//			gc.setFill(figureColorMap.get(figure));
//			figure.redraw(gc);
//		}
//
//	}
//
//	private Figure findFigureAtPoint(Point point) {
//		for (Figure figure : canvasState.figures()) {
//			if (figure.containsPoint(point)) {
//				return figure;
//			}
//		}
//		return null;
//	}
//
//	private boolean figureBelongs(Figure figure, Point eventPoint) {
//		return figure.containsPoint(eventPoint);
//	}
//
//}

//BUTTON IMPLEMENTATION

public class PaintPane extends BorderPane {
	private CanvasState canvasState;
	private Canvas canvas = new Canvas(800, 600);
	private GraphicsContext gc = canvas.getGraphicsContext2D();
	private Color lineColor = Color.BLACK;
	private Color defaultFillColor = Color.YELLOW;
	private Point startPoint;
	private Figure selectedFigure;
	private StatusPane statusPane;
	private Map<Figure, Color> figureColorMap = new HashMap<>();
	private ToolButton currentTool;
	private ToggleGroup toolsGroup = new ToggleGroup();

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
		ColorPicker fillColorPicker = new ColorPicker(defaultFillColor);
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
		figureColorMap.put(figure, defaultFillColor);
		canvasState.addFigure(figure);
	}

	public void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Figure figure : canvasState.figures()) {
			gc.setStroke(figure == selectedFigure ? Color.RED : lineColor);
			gc.setFill(figureColorMap.get(figure));
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
		figureColorMap.remove(figure);
		canvasState.deleteFigure(figure);
		redrawCanvas();
	}

}