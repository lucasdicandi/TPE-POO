package frontend.Buttons.ColorPicker;

import frontend.PaintPane;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class FillColorPickerSecondary extends ColorPicker {
    public FillColorPickerSecondary(PaintPane paintPane, Color defaultColor){
        super(defaultColor);
        this.setOnAction(event ->{
            if (paintPane.getSelectedFigure() != null) {
                paintPane.getSelectedFigure().setSecondaryColor(this.getValue());
                paintPane.redrawCanvas();
            }
        });
    }
}
