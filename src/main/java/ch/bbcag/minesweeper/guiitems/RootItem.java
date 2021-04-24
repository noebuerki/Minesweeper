package ch.bbcag.minesweeper.guiitems;

import javafx.scene.canvas.GraphicsContext;

public abstract class RootItem {

    protected Point topLeft;
    protected GraphicsContext gc;

    public RootItem(Point topLeft, GraphicsContext gc){

        this.topLeft = topLeft;
        this.gc = gc;
    }

    public abstract void draw();
}