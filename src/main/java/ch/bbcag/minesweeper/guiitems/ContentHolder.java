package ch.bbcag.minesweeper.guiitems;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ContentHolder extends RootItem {

    private Image image;

    public ContentHolder(Point topLeft, Image image, GraphicsContext gc){

        super(topLeft, gc);
        this.image = image;
    }

    public void draw(){
        gc.drawImage(image, topLeft.getX(), topLeft.getY());
    }
}
