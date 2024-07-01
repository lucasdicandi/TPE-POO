package frontend.Buttons;

import backend.model.Figure;
import backend.model.Point;
import frontend.PaintPane;

public class DivideToolButton extends ToolButton{

    PaintPane paintPane;

    Point startPointOriginal;
    Point EndintOriginal;

    public DivideToolButton(PaintPane paintPane) {
        super("Dividir");
        this.paintPane = paintPane;
        this.setOnMouseClicked(event -> onMouseClicked(paintPane, event.getX(), event.getY()));
    }

    @Override
    public void onMouseClicked(PaintPane paintPane, double x, double y) {
        Figure selectedFigure = paintPane.getSelectedFigure();
        if(selectedFigure != null) {
            Figure[] dividedFigures = selectedFigure.divide(); // Assuming divide method is implemented correctly
            paintPane.removeFigure(selectedFigure); // Remove the original figure
            for (Figure figure : dividedFigures) {
                paintPane.addFigure(figure);
            }
            paintPane.redrawCanvas();
        }
    }



}
