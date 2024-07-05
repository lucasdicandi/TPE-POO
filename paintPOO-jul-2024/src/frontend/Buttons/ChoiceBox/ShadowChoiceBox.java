package frontend.Buttons.ChoiceBox;

import backend.model.Figure;
import backend.model.ShadowType;
import frontend.PaintPane;
import javafx.scene.control.ChoiceBox;

public class ShadowChoiceBox extends ChoiceBox<ShadowType> {

    public ShadowChoiceBox(PaintPane paintPane) {
        this.getItems().addAll(ShadowType.values());
        this.setValue(ShadowType.NONE);
        this.setOnAction(event -> {
            if(paintPane.getSelectedFigure() != null) {
                paintPane.getSelectedFigure().setShadowType(this.getValue());
                paintPane.redrawCanvas();
            }
        });
    }
}
