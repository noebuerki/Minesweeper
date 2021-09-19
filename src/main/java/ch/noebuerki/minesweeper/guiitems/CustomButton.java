package ch.noebuerki.minesweeper.guiitems;

import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class CustomButton{

    public static Button createButton(Image image, Point point) {

        Button button = new Button();

        button.setGraphic(new ImageView(image));
        button.setMinWidth(image.getWidth());
        button.setMinHeight(image.getHeight());

        button.setLayoutX(point.getX());
        button.setLayoutY(point.getY());
        button.setBackground(Background.EMPTY);
        button.setBorder(Border.EMPTY);

        return button;
    }
}
