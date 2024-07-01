package frontend.Buttons;

import backend.model.Figure;
import frontend.PaintPane;

public class DeleteToolButton extends ToolButton {
    private PaintPane paintPane;

    public DeleteToolButton(PaintPane paintPane) {
        super("Borrar");
        this.paintPane = paintPane;
        this.setOnMouseClicked(event -> onMouseClicked(paintPane, event.getX(), event.getY()));
    }

    @Override
    public void onMouseClicked(PaintPane paintPane, double x, double y) {
        Figure selectedFigure = paintPane.getSelectedFigure();
        if (selectedFigure != null) {
            paintPane.removeFigure(selectedFigure);
            paintPane.setSelectedFigure(null);
            paintPane.redrawCanvas();
        }
    }

}


