package frontend.Buttons.ChoiceBox;

import frontend.PaintPane;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class LayerChoiceBox extends ChoiceBox<String> {
    public LayerChoiceBox(PaintPane paintPane){
        this.setItems(FXCollections.observableArrayList(paintPane.getLayersMap().keySet()));

        this.setValue("Capa 1");

        this.setOnAction(event -> {
            if(paintPane != null) {
                paintPane.getSelectedFigure().setLayer(paintPane.getLayersMap().getOrDefault(this.getValue(), 1));
                paintPane.redrawCanvas();
            }
        });
    }
}

