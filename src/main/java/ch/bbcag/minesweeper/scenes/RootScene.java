package ch.bbcag.minesweeper.scenes;

import ch.bbcag.minesweeper.guiitems.*;
import ch.bbcag.minesweeper.navigation.Navigator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class RootScene extends Scene {

    protected Navigator nav;
    protected Group root;
    protected AnchorPane pane;
    protected Canvas sceneCanvas;
    protected GraphicsContext gc;
    protected Point mouseClick;
    protected List<RootItem> guiItems = new CopyOnWriteArrayList<>();

    public RootScene(Navigator nav) {

        super(new Group());
        root = (Group) super.getRoot();
        this.nav = nav;
        sceneCanvas = new Canvas(Dimensions.WIDTH, Dimensions.HEIGHT);
        pane = new AnchorPane();
        pane.setMinHeight(Dimensions.HEIGHT);
        pane.setMinWidth(Dimensions.WIDTH);
        gc = sceneCanvas.getGraphicsContext2D();
        root.getChildren().addAll(sceneCanvas, pane);
    }

    public void drawScene(){
        gc.clearRect(0,0,Dimensions.WIDTH,Dimensions.HEIGHT);
        for(RootItem item : guiItems){
            item.draw();
        }
    }
}
