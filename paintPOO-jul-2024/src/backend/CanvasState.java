package backend;

import backend.model.Figure;
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

    public Iterable<Figure> figures() {
        list.sort(comparator());
        return list;
    }

    public Iterable<Figure> figuresReversed() {
        list.sort(comparator());
        return list.reversed();
    }



    public Comparator<Figure> comparator(){
        return Comparator.comparingInt(Figure::getLayer);
    }


}





