package frontend.Buttons.RadioButton;

import backend.model.Figure;
import frontend.PaintPane;
import javafx.scene.control.RadioButton;

public class ShowLayerRadioButton extends RadioButton {
    public ShowLayerRadioButton(PaintPane paintPane) {
        super("Mostrar");
        this.setOnMousePressed(event -> {
            for(Figure figure : paintPane.getCanvasState().figures()){
                if(figure.getLayer() == paintPane.getLayersMap().get(paintPane.getLayerChoiceBox().getValue())) {
                    figure.setShow(true);
                }
            }
        });
    }
}
