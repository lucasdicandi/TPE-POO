package frontend.Buttons.Slider;

import frontend.PaintPane;
import javafx.scene.control.Slider;

public class LineWithSliderButton extends Slider {
    public LineWithSliderButton(double v, double v1, double v2, PaintPane paintPane){
        super(v, v1, v2);
        this.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (paintPane.getSelectedFigure() != null) {
                paintPane.getSelectedFigure().setLineWidth(newVal.doubleValue());
                paintPane.redrawCanvas();
            }
        });
    }
}
