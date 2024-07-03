package backend;

import backend.model.Figure;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class CanvasState {
    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
        list.sort((o1, o2) -> {
            int cmp = Integer.compare(o1.getLayer(), o2.getLayer());
                if (cmp == 0) {
                    return o1.equals(o2) ? 0 : 1;
                }
             return cmp;
            });
    }

    public void deleteFigure(Figure figure) {
        list.remove(figure);
    }

    public Iterable<Figure> figures() {
        list.sort((o1, o2) -> {
            int cmp = Integer.compare(o1.getLayer(), o2.getLayer());
            if (cmp == 0) {
                return o1.equals(o2) ? 0 : 1;
            }
            return cmp;
        });
        return new ArrayList<>(list);
    }

    public void deleteFiguresInLayer(int layer) {
        list.removeIf(figure -> figure.getLayer() == layer);
    }


}





