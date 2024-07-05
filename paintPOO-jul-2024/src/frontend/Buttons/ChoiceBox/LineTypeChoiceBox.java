package frontend.Buttons.ChoiceBox;

import backend.model.LineType;
import javafx.scene.control.ChoiceBox;
import frontend.PaintPane;


public class LineTypeChoiceBox extends ChoiceBox<LineType> {
    public LineTypeChoiceBox(PaintPane paintPane) {
        this.getItems().addAll(LineType.values());
        this.setValue(LineType.NORMAL);
        this.setOnAction(event -> {
            if(paintPane.getSelectedFigure() != null) {
                paintPane.getSelectedFigure().setLineType(this.getValue());
                paintPane.redrawCanvas();
            }
        });
    }
}
