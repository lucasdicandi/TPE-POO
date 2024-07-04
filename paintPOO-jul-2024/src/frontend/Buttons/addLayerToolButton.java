package frontend.Buttons;

import frontend.PaintPane;

public class addLayerToolButton extends ToolButton{

    public addLayerToolButton(PaintPane paintPane) {
        super("Agregar Capa");

        this.setOnAction(event -> {
            String newLayer = "Capa " + paintPane.getNextLayerNumber();
            paintPane.getLayersMap().put(newLayer, paintPane.getNextLayerNumber());
            paintPane.incrementNextLayerNumber();
            paintPane.getLayerChoiceBox().getItems().add(newLayer);
            paintPane.getLayerChoiceBox().setValue(newLayer);
            paintPane.getLayerChoiceBox().setValue("Capa 1");
            paintPane.redrawCanvas();
        });
    }
}
