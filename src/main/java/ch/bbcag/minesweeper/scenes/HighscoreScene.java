package ch.bbcag.minesweeper.scenes;

import ch.bbcag.minesweeper.FileManager;
import ch.bbcag.minesweeper.guiitems.*;
import ch.bbcag.minesweeper.enums.*;
import ch.bbcag.minesweeper.navigation.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class HighscoreScene extends RootScene implements Initializable {
    private TextHolder highOneLbl;
    private TextHolder highTwoLbl;
    private TextHolder highThreeLbl;

    public HighscoreScene(Navigator nav) {

        super(nav);

        gc.setFont(Font.loadFont(getClass().getResource("/fonts/LexendDeca-Regular.ttf").toExternalForm(), 60));

        // Title
        guiItems.add(new ContentHolder(new Point(50, 50), new Image("/texts/highscore.png"), gc));

        // Icons
        guiItems.add(new ContentHolder(new Point(1000, 50), new Image("/icons/trophy_small.png"), gc));

        // Scores
        guiItems.add(new ContentHolder(new Point(152, 320), new Image("/texts/table_highscore.png"), gc));

        highOneLbl = new TextHolder(new Point(520, 545), "0", gc);
        highTwoLbl = new TextHolder(new Point(717, 545), "0", gc);
        highThreeLbl = new  TextHolder(new Point(916, 545), "0", gc);

        guiItems.add(highOneLbl);
        guiItems.add(highTwoLbl);
        guiItems.add(highThreeLbl);

        // Buttons

        Button button_back = CustomButton.createButton(new Image("/buttons/back.png"), new Point(1070, 730));
        button_back.setOnAction(event -> nav.navigateTo(SceneType.START));

        Button button_reset = CustomButton.createButton(new Image("/buttons/reset.png"), new Point(940, 730));
        button_reset.setOnAction(event -> {
            FileManager.createFile();
            initialize();
        });

        pane.getChildren().addAll(button_back, button_reset);
    }

    @Override
    public void initialize() {

        highOneLbl.setText(Integer.toString(FileManager.getHighscore(Profile.ONE)));
        highTwoLbl.setText(Integer.toString(FileManager.getHighscore(Profile.TWO)));
        highThreeLbl.setText(Integer.toString(FileManager.getHighscore(Profile.THREE)));

        drawScene();
    }
}
