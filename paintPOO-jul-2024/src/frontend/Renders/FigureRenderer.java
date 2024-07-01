package frontend.Renders;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public abstract class FigureRenderer {
    public abstract void render(Figure figure, GraphicsContext gc);
}