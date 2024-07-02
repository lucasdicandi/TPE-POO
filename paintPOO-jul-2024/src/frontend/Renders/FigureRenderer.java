package frontend.Renders;

import backend.model.Figure;
import backend.model.ShadowType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class FigureRenderer {
    public abstract void render(Figure figure, GraphicsContext gc);


    public void renderShadow(Figure figure, GraphicsContext gc, Color color) {
        Figure shadow = figure.clone();
        shadow.setColor(color);
        shadow.draw(figure.getShadowType().getOffset(), figure.getShadowType().getOffset());
        render(shadow, gc);
    }

}