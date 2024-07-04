package frontend.Buttons.ToolButton;

import backend.model.Figure;
import frontend.PaintPane;

import java.util.Objects;

public class DeleteLayerToolButton extends ToolButton{
    public DeleteLayerToolButton(PaintPane paintPane) {
        super("Eliminar Capa");
        this.setOnAction(event -> {
            String selectedLayer = paintPane.getLayerChoiceBox().getValue();
            if (!selectedLayer.equals("Capa 1") && !selectedLayer.equals("Capa 2") && !selectedLayer.equals("Capa 3")) {
                paintPane.getLayersMap().remove(selectedLayer);
                paintPane.getLayerChoiceBox().getItems().remove(selectedLayer);
                for(Figure figure : paintPane.getCanvasState().figures()) {
                    if (Objects.equals(paintPane.getLayerChoiceBox().getValue(), "Capa " + figure.getLayer())){
                        paintPane.getCanvasState().deleteFigure(figure);
                    }
                }
                paintPane.redrawCanvas();
                paintPane.getLayerChoiceBox().setValue("Capa 1");
                paintPane.redrawCanvas();
            }
        });
    }

}
