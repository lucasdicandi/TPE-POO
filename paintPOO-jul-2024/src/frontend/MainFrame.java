package frontend;

import backend.CanvasState;
import frontend.Buttons.ButtonBox;
import javafx.scene.layout.VBox;

public class
MainFrame extends VBox {

    public MainFrame(CanvasState canvasState) {
        getChildren().add(new AppMenuBar());
        StatusPane statusPane = new StatusPane();

        PaintPane paintPane = new PaintPane(statusPane);
        getChildren().add(paintPane);
        ButtonBox buttonBox = new ButtonBox(paintPane, canvasState);
        paintPane.addButtonBox(buttonBox);
        getChildren().add(statusPane);

    }

}
