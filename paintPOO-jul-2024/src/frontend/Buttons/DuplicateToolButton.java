package frontend.Buttons;


import backend.model.Figure;
import frontend.PaintPane;

public class DuplicateToolButton extends ToolButton {

    private final PaintPane paintPane;
    private static final int OFFSET = 20; // Define the offset for the duplication

    public DuplicateToolButton(PaintPane paintPane) {
        super("Duplicate");
        this.paintPane = paintPane;
        this.setOnMouseClicked(event -> onMouseClicked(paintPane, event.getX(), event.getY()));
    }


    @Override
    public void onMouseClicked(PaintPane paintPane, double x, double y) {
        Figure selectedFigure = paintPane.getSelectedFigure();
        if(selectedFigure != null){
            Figure duplicatedFigure = selectedFigure.clone();
            duplicatedFigure.draw(OFFSET, OFFSET);
            paintPane.addFigure(duplicatedFigure);
            paintPane.redrawCanvas();
        }else {
            System.out.println("No figure selected for duplication."); // Debug message
        }
    }
}
