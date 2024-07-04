package frontend.Buttons.ChoiceBox;

import backend.model.Figure;
import backend.model.ShadowType;
import frontend.PaintPane;
import javafx.scene.control.ChoiceBox;

public class ShadowChoiceBox extends ChoiceBox<ShadowType> {

    public ShadowChoiceBox(PaintPane paintPane) {
        super();
        this.getItems().addAll(ShadowType.values());
        this.setValue(ShadowType.NONE);

        this.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            onShadowTypeChanged(paintPane ,newValue);
        });
    }

    private void onShadowTypeChanged(PaintPane paintPane, ShadowType newShadowType) {
        if (selectedFigure != null) {
            selectedFigure.setShadowType(newShadowType);
        }
    }

    private Figure selectedFigure;

    public void setSelectedShadow(Figure figure) {
        this.selectedFigure = figure;
        if (figure != null) {
            this.setValue(figure.getShadowType());
        } else {
            this.setValue(ShadowType.NONE);
        }
    }
}
