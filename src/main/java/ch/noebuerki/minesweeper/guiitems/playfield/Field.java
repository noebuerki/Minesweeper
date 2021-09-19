package ch.noebuerki.minesweeper.guiitems.playfield;

import ch.bbcag.minesweeper.guiitems.*;
import ch.bbcag.minesweeper.enums.*;
import ch.noebuerki.minesweeper.enums.FieldContent;
import ch.noebuerki.minesweeper.enums.FieldStatus;
import ch.noebuerki.minesweeper.guiitems.Point;
import ch.noebuerki.minesweeper.guiitems.RootItem;
import ch.noebuerki.minesweeper.scenes.GameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class Field extends RootItem {

    private static final int width = 30;
    private static final int height = 30;
    private static final Image flagImg = new Image("fields/flag.png");
    private static final Image coverImg = new Image("fields/covered.png");
    private static boolean isGameRunning = true;
    private static boolean isGameWon = false;
    private static boolean isGameLost = false;
    private static List<Field> playField = new ArrayList<>();
    private static GameScene parentScene;

    private Point topRight;
    private Point bottomLeft;
    private FieldContent content;
    private Image contentImg;
    private Image image = coverImg;
    private FieldStatus status = FieldStatus.COVERED;
    private List<Field> neighbours = new ArrayList<>();

    public Field(Point topLeft, GraphicsContext gc, GameScene scene) {

        super(topLeft, gc);

        parentScene = scene;
        topRight = new Point(topLeft.getX() + width, topLeft.getY());
        bottomLeft = new Point(topLeft.getX(), topLeft.getY() + height);
    }

    public void checkForClick(MouseEvent click) {

        if (click.getX() >= topLeft.getX() && click.getX() <= topRight.getX() &&
                click.getY() >= topLeft.getY() && click.getY() <= bottomLeft.getY() && isGameRunning) {

            if (click.getButton().name().equals("PRIMARY")) {
                checkUncover();
            }

            if (click.getButton().name().equals("SECONDARY")) {
                checkMark();
            }

            draw();

            isGameWon = true;
            for (Field field : playField) {
                if (field.getContent() != FieldContent.BOMB && field.getStatus() != FieldStatus.UNCOVERED) {
                    isGameWon = false;
                    break;
                }
            }
        }
    }

    public void checkUncover() {

        if (status == FieldStatus.COVERED) {
            image = contentImg;
            status = FieldStatus.UNCOVERED;
            if (content == FieldContent.EMPTY) {
                for (Field neighbour : neighbours) {
                    neighbour.checkUncover();
                    neighbour.draw();
                }
            } else if (content == FieldContent.BOMB && isGameRunning) {
                isGameLost = true;
            }
            draw();
        }
    }

    public void checkMark() {

        if (status == FieldStatus.COVERED) {
            image = flagImg;
            status = FieldStatus.MARKED;
            parentScene.getFlagCounter().setText(Integer.toString(Integer.parseInt(parentScene.getFlagCounter().getText()) - 1));
        } else if (status == FieldStatus.MARKED) {
            image = coverImg;
            status = FieldStatus.COVERED;
            parentScene.getFlagCounter().setText(Integer.toString(Integer.parseInt(parentScene.getFlagCounter().getText()) + 1));
        }
    }

    @Override
    public void draw() {
        gc.drawImage(image, topLeft.getX(), topLeft.getY());
    }

    public FieldStatus getStatus() {
        return status;
    }

    public void setContent(FieldContent content) {

        this.content = content;

        contentImg = new Image("fields/"+content.toString().toLowerCase()+".png");
    }

    public FieldContent getContent() {
        return content;
    }

    public List<Field> getNeighbours() {
        return neighbours;
    }

    public static void isGameRunning(Boolean running) {
        isGameRunning = running;
    }

    public static boolean isGameRunning() {
        return isGameRunning;
    }

    public static void isGameLost(boolean lost){
        isGameLost = lost;
    }

    public static boolean isGameLost(){
        return isGameLost;
    }

    public static void isGameWon(boolean won){
        isGameWon = won;
    }

    public static boolean isGameWon() {
        return isGameWon;
    }

    public static void setPlayField(List<Field> playFieldArray) {
        playField = playFieldArray;
    }
}
