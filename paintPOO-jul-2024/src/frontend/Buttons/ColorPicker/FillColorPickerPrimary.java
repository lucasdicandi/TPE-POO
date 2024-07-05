package frontend.Buttons.ColorPicker;

import backend.model.ShadowType;
import frontend.PaintPane;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class FillColorPickerPrimary extends ColorPicker {
    public FillColorPickerPrimary(PaintPane paintPane, Color defaultColor){
            super(defaultColor);
            this.setOnAction(event ->{
            if (paintPane != null) {
                paintPane.getSelectedFigure().setColor(this.getValue());
                paintPane.getShadowRendererMap().put(ShadowType.COLORED, this.getValue().darker());
                paintPane.getShadowRendererMap().put(ShadowType.COLORED_INVERSE, this.getValue().darker());
                paintPane.redrawCanvas();
            }
        });
    }
}
