package frontend.Buttons;


import backend.CanvasState;
import backend.model.Figure;
import backend.model.LineType;
import backend.model.Point;
import backend.model.ShadowType;
import frontend.Buttons.ChoiceBox.LineTypeChoiceBox;
import frontend.Buttons.ChoiceBox.ShadowChoiceBox;
import frontend.Buttons.ColorPicker.FillColorPickerPrimary;
import frontend.Buttons.ColorPicker.FillColorPickerSecondary;
import frontend.Buttons.RadioButton.HideLayerRadioButton;
import frontend.Buttons.RadioButton.ShowLayerRadioButton;
import frontend.Buttons.Slider.LineWithSliderButton;
import frontend.Buttons.ToolButtons.*;
import frontend.PaintPane;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ButtonBox extends Pane {

    private final PaintPane paintPane;
    private final Color defaultFillColor = Color.YELLOW;
    private ToolButton currentTool;
    private final ToggleGroup toolsGroup = new ToggleGroup();
    private final FillColorPickerPrimary fillColorPickerPrimary;
    private final FillColorPickerSecondary fillColorPickerSecondary;
    private final Map<String, Integer> layersMap = new TreeMap<>();
    private final Map<ShadowType, Color> shadowRendererMap = new HashMap<>();
    private final ShadowChoiceBox shadowChoiceBox;
    private final LineTypeChoiceBox lineTypeChoiceBox;
    private final ChoiceBox<String> layerChoiceBox = new ChoiceBox<>();
    private final ShowLayerRadioButton showLayerButton;
    private final HideLayerRadioButton hideLayerButton;
    private final LineWithSliderButton lineWithSlider;
    private static final int INITIAL_LAYER = 4;
    private int nextLayerNumber = INITIAL_LAYER;
    private final AddLayerToolButton addLayerButton;
    private final DeleteLayerToolButton deleteLayerButton;

    private final CanvasState canvasState;

    public CanvasState getCanvasState() {
        return canvasState;
    }

    public ButtonBox(PaintPane paintPane, CanvasState canvasState){
        this.paintPane = paintPane;
        this.canvasState = canvasState;
        fillColorPickerPrimary = new FillColorPickerPrimary(paintPane, defaultFillColor);
        fillColorPickerSecondary = new FillColorPickerSecondary(paintPane, defaultFillColor);
        shadowChoiceBox = new ShadowChoiceBox(paintPane);
        lineTypeChoiceBox = new LineTypeChoiceBox(paintPane);
        showLayerButton = new ShowLayerRadioButton(paintPane);
        hideLayerButton = new HideLayerRadioButton(paintPane);
        lineWithSlider = new LineWithSliderButton(0, 10, 5, paintPane);
        addLayerButton = new AddLayerToolButton(paintPane);
        deleteLayerButton = new DeleteLayerToolButton(paintPane);
    }

    public void initialize(){
        initializeTools();
        initializeUI();
        setupCanvasEvents();
    }

    public void setupCanvasEvents() {
        paintPane.getCanvas().setOnMousePressed(event -> {
            if (getCurrentTool() != null) {
                getCurrentTool().onMousePressed(paintPane, event.getX(), event.getY());
            }
        });

        paintPane.getCanvas().setOnMouseReleased(event -> {
            if (getCurrentTool() != null) {
                getCurrentTool().onMouseReleased(paintPane, event.getX(), event.getY());
            }
        });

        paintPane.getCanvas().setOnMouseClicked(event -> {
            if (getCurrentTool() != null) {
                getCurrentTool().onMouseClicked(paintPane, event.getX(), event.getY());
            }

        });

        paintPane.getCanvas().setOnMouseDragged(event -> {
            if (getCurrentTool() != null) {
                getCurrentTool().onMouseDragged(paintPane, event.getX(), event.getY());
            }
        });

        paintPane.getCanvas().setOnMouseMoved(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());
            Figure hoveredFigure = paintPane.findFigureAtPoint(eventPoint);
            paintPane.getStatusPane().updateStatus(hoveredFigure != null ? hoveredFigure.toString() : eventPoint.toString());
        });
    }

    private void initializeUI() {
        VBox buttonsBox = new VBox(10);
        renderChoiceButtons(buttonsBox);
        HBox topBar = new HBox(10);
        renderHBox(topBar);
        paintPane.setTop(topBar);
        paintPane.setLeft(buttonsBox);
    }

    private void initializeTools() {
        ToolButton[] toolsArr = {
                new SelectionToolButton(),
                new RectangleToolButton(paintPane),
                new CircleToolButton(paintPane),
                new SquareToolButton(paintPane),
                new EllipseToolButton(paintPane),
                new DeleteToolButton(paintPane),
        };

        for (ToolButton tool : toolsArr) {
            tool.setToggleGroup(toolsGroup);
            tool.setOnAction(event -> {
                currentTool = tool;
            });
        }

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
        paintPane.setLeft(buttonsBox);
        paintPane.setRight(paintPane.getCanvas());
        buttonsBox.setSpacing(10);
        lineWithSlider.setShowTickMarks(true);
        lineWithSlider.setShowTickLabels(true);
        buttonsBox.getChildren().addAll(new Label("Borde:"), lineWithSlider, lineTypeChoiceBox);
        buttonsBox.getChildren().addAll(new Label("Acciones:"), new DuplicateToolButton(paintPane), new DivideToolButton(paintPane), new CenterToolButton(paintPane));
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
            if(paintPane.getSelectedFigure() != null) {
                paintPane.getSelectedFigure().setLayer(layersMap.getOrDefault(layerChoiceBox.getValue(), 1));
                paintPane.redrawCanvas();
            }
        });

        ToggleGroup visibilityGroup = new ToggleGroup();
        showLayerButton.setToggleGroup(visibilityGroup);
        hideLayerButton.setToggleGroup(visibilityGroup);
        showLayerButton.setSelected(true);
        visibilityGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle != null) {
                layerChoiceBox.getValue();
                paintPane.redrawCanvas();
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
    public ToolButton getCurrentTool() {
        return currentTool;
    }

}

