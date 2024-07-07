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
        return Comparator.comparingInt(Figure::getLayer);
    }


}





