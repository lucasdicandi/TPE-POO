package frontend.Buttons.ToolButtons;

import frontend.PaintPane;

public class AddLayerToolButton extends ToolButton{

    public AddLayerToolButton(PaintPane paintPane) {
        super("Agregar Capa");

        this.setOnAction(event -> {
            String newLayer = "Capa " + paintPane.getNextLayerNumber();
            paintPane.getLayersMap().put(newLayer, paintPane.getNextLayerNumber());
            paintPane.incrementNextLayerNumber();
            paintPane.getLayerChoiceBox().getItems().add(newLayer);
            paintPane.getLayerChoiceBox().setValue(newLayer);
            paintPane.redrawCanvas();
        });
    }
}
