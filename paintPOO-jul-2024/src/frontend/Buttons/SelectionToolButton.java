package frontend.Buttons;

import backend.model.Figure;
import backend.model.LineType;
import backend.model.Point;
import backend.model.ShadowType;
import frontend.PaintPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SelectionToolButton extends ToolButton {

    private double initialX, initialY;

    public SelectionToolButton() {
        super("Seleccionar");
    }

    @Override
    public void onMousePressed(PaintPane paintPane, double x, double y) {
        initialX = x;
        initialY = y;
        paintPane.setSelectedFigure(null);

        Point point = new Point(x, y);
        Figure f = paintPane.findFigureAtPoint(point);
        paintPane.setSelectedFigure(f);

        for (Figure figure : paintPane.getCanvasState().figures().reversed()) {
            if (figure.containsPoint(point)){
                paintPane.setSelectedFigure(figure);
                break;
            }
        }
    }

    @Override
    public void onMouseDragged(PaintPane paintPane, double x, double y) {
        Figure selectedFigure;
        if(paintPane.getSelectedFigure() == null) {
            Point point = new Point(x, y);
            selectedFigure = paintPane.findFigureAtPoint(point);
        }else{
            selectedFigure = paintPane.getSelectedFigure();
        }

        if (selectedFigure != null) {
            double deltaX = x - initialX ;
            double deltaY = y - initialY ;
            selectedFigure.draw(deltaX, deltaY);
            initialX = x;
            initialY = y;
            paintPane.redrawCanvas();
        }
        paintPane.setSelectedFigure(selectedFigure);
    }

    @Override
    public void onMouseClicked(PaintPane paintPane, double x, double y) {
        Point point = new Point(x, y);
        Figure figure = paintPane.findFigureAtPoint(point);
        paintPane.setSelectedFigure(figure);
        paintPane.getStatusPane().updateStatus(figure != null ? "Selected: " + figure : "No figure found");

        if(figure != null) {
            paintPane.getLayerChoiceBox().setValue("Capa %d".formatted(figure.getLayer()));
            paintPane.getFillColorPickerPrimary().setValue(figure.getColor());
            paintPane.getFillColorPickerSecondary().setValue(figure.getSecondaryColor());
            paintPane.getShadowChoiceBox().setValue(figure.getShadowType());
            paintPane.getLineTypeChoiceBox().setValue(figure.getLineType());
        }else{
            paintPane.getShadowChoiceBox().setValue(ShadowType.NONE);
            paintPane.getLineTypeChoiceBox().setValue(LineType.NORMAL);
        }

        paintPane.redrawCanvas();
    }

    @Override
    public void onMouseReleased(PaintPane paintPane, double x, double y) {
        Point point = new Point(x, y);
        Figure figure = paintPane.findFigureAtPoint(point);
        paintPane.setSelectedFigure(figure);
    }
}





