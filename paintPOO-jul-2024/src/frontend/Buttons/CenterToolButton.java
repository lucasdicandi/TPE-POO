package frontend.Buttons;

import backend.model.Figure;
import frontend.PaintPane;

public class CenterToolButton extends ToolButton{

    private final PaintPane paintPane;

    public CenterToolButton(PaintPane paintPane) {
        super("Centrar");
        this.paintPane = paintPane;
        this.setOnMouseClicked(event -> onMouseClicked(paintPane, event.getX(), event.getY()));
    }

    @Override
    public void onMouseClicked(PaintPane paintPane, double x, double y) {
        Figure selectedFigure = paintPane.getSelectedFigure();
        if(selectedFigure != null){
            selectedFigure.moveToCenter(paintPane.getCanvasWidth() / 2, paintPane.getCanvasHeight() / 2);
            paintPane.redrawCanvas();
        }
    }

}
