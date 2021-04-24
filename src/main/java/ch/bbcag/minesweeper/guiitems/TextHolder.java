package ch.bbcag.minesweeper.guiitems;

import javafx.scene.canvas.GraphicsContext;

public class TextHolder extends RootItem {

    private String text;

    public TextHolder(Point topLeft, String text, GraphicsContext gc) {

        super(topLeft, gc);
        this.text = text;
    }

    public void draw(){

        gc.fillText(text, topLeft.getX(), topLeft.getY());
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}