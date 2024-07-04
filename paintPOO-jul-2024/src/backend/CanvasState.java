package backend;

import backend.model.Figure;
import frontend.PaintPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class CanvasState {
    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
        list.sort(comparator());
    }

    public void deleteFigure(Figure figure) {
        list.remove(figure);
    }

    public List<Figure> figures() {
        list.sort(comparator());
        return new ArrayList<>(list);
    }

    private Comparator<Figure> comparator(){
        return (o1, o2) -> {
            int cmp = Integer.compare(o1.getLayer(), o2.getLayer());
            if (cmp == 0) {
                return o1.equals(o2) ? 1 : 0;
            }
            return cmp;
        };
    }


}





