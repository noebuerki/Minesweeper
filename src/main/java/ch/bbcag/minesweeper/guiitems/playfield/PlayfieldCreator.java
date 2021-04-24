package ch.bbcag.minesweeper.guiitems.playfield;


import ch.bbcag.minesweeper.guiitems.Point;
import ch.bbcag.minesweeper.enums.*;
import ch.bbcag.minesweeper.scenes.GameScene;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayfieldCreator {

    private static int bombcount;
    private static int bombcounter;
    private static Field[][] fieldArray;
    private static List<Field> returnArray;
    private static GraphicsContext gc;
    private static GameScene parentScene;

    public static List<Field> createPlayfield(Difficulty difficulty, GraphicsContext graphicsContext, GameScene scene) {

        parentScene = scene;
        gc = graphicsContext;
        returnArray = new ArrayList<>();
        fieldArray = new Field[23][23];

        switch (difficulty) {
            case EASY:
                bombcount = 20;
                break;

            case MEDIUM:
                bombcount = 40;
                break;

            case HARD:
                bombcount = 80;
                break;
        }

        generateFieldArray();
        defineBombFields();
        defineNeighbours();
        defineContent();

        for(int Xpos = 1; Xpos < 22; Xpos++) {
            returnArray.addAll(Arrays.asList(fieldArray[Xpos]).subList(1, 22));
        }

        Field.setPlayField(returnArray);
        return returnArray;
    }

    public static void generateFieldArray() {

        int Xcor = 435;
        int Ycor = 35;

        for(int Xpos = 1; Xpos < 22; Xpos++) {

            for(int Ypos = 1; Ypos < 22; Ypos++){
                fieldArray[Xpos][Ypos] = new Field(new Point(Xcor, Ycor), gc, parentScene);
                Ycor += 35;
            }

            Ycor = 35;
            Xcor += 35;
        }
    }

    public static void defineBombFields() {

        bombcounter = 0;

        for (int Xpos = 1; Xpos < 22; Xpos++) {

            for (int Ypos = 1; Ypos < 22; Ypos++) {

                if(Math.random() < 1d / (441d / (double) bombcount) && bombcounter < bombcount) {
                    fieldArray[Xpos][Ypos].setContent(FieldContent.BOMB);
                    bombcounter++;
                }
            }
        }
    }

    public static void defineNeighbours() {

        for (int Xpos = 1; Xpos < 22; Xpos++) {
            for (int Ypos = 1; Ypos < 22; Ypos++) {

                if(fieldArray[Xpos-1][Ypos-1] != null) {
                    fieldArray[Xpos][Ypos].getNeighbours().add(fieldArray[Xpos-1][Ypos-1]);
                }

                if(fieldArray[Xpos][Ypos-1] != null) {
                    fieldArray[Xpos][Ypos].getNeighbours().add(fieldArray[Xpos][Ypos-1]);
                }

                if(fieldArray[Xpos+1][Ypos-1] != null) {
                    fieldArray[Xpos][Ypos].getNeighbours().add(fieldArray[Xpos+1][Ypos-1]);
                }

                if(fieldArray[Xpos+1][Ypos] != null) {
                    fieldArray[Xpos][Ypos].getNeighbours().add(fieldArray[Xpos+1][Ypos]);
                }

                if(fieldArray[Xpos+1][Ypos+1] != null) {
                    fieldArray[Xpos][Ypos].getNeighbours().add(fieldArray[Xpos+1][Ypos+1]);
                }

                if(fieldArray[Xpos][Ypos+1] != null) {
                    fieldArray[Xpos][Ypos].getNeighbours().add(fieldArray[Xpos][Ypos+1]);
                }

                if(fieldArray[Xpos-1][Ypos+1] != null) {
                    fieldArray[Xpos][Ypos].getNeighbours().add(fieldArray[Xpos-1][Ypos+1]);
                }

                if(fieldArray[Xpos-1][Ypos] != null) {
                    fieldArray[Xpos][Ypos].getNeighbours().add(fieldArray[Xpos-1][Ypos]);
                }
            }
        }
    }

    public static void defineContent() {

        int bombcounter;

        for (int Xpos = 1; Xpos < 22; Xpos++) {
            for (int Ypos = 1; Ypos < 22; Ypos++) {
                if (fieldArray[Xpos][Ypos].getContent() != FieldContent.BOMB) {
                    bombcounter = 0;

                    for (Field field : fieldArray[Xpos][Ypos].getNeighbours()) {
                        if (field.getContent() == FieldContent.BOMB) {
                            bombcounter++;
                        }
                    }

                    fieldArray[Xpos][Ypos].setContent(FieldContent.values()[bombcounter]);
                }
            }
        }
    }

    public static int getBombcounter() {
        return bombcounter;
    }
}
