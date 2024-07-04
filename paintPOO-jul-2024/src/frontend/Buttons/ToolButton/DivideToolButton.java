package frontend.Buttons.ToolButton;

import backend.model.Figure;
import frontend.PaintPane;

public class DivideToolButton extends ToolButton{

    PaintPane paintPane;

    public DivideToolButton(PaintPane paintPane) {
        super("Dividir");
        this.paintPane = paintPane;
        this.setOnMouseClicked(event -> onMouseClicked(paintPane, event.getX(), event.getY()));
    }

    @Override
    public void onMouseClicked(PaintPane paintPane, double x, double y) {
        Figure selectedFigure = paintPane.getSelectedFigure();
        if(selectedFigure != null) {
            Figure[] dividedFigures = selectedFigure.divide();
            paintPane.removeFigure(selectedFigure);
            for (Figure figure : dividedFigures) {
                paintPane.addFigure(figure);
            }
            paintPane.redrawCanvas();
        }
    }



}
