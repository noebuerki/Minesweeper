package ch.bbcag.minesweeper.scenes;

import ch.bbcag.minesweeper.FileManager;
import ch.bbcag.minesweeper.guiitems.*;
import ch.bbcag.minesweeper.enums.*;
import ch.bbcag.minesweeper.navigation.*;
import ch.bbcag.minesweeper.navigation.params.WinNavParams;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class WinScene extends RootScene implements Initializable, Parameterized<WinNavParams> {

    private Difficulty difficulty;
    private Profile profile;
    private TextHolder highscoreLbl;
    private TextHolder scoreLbl;
    private int bombCount;
    private int gameTime;

    public WinScene(Navigator nav) {

        super(nav);

        gc.setFont(Font.loadFont(getClass().getResource("/fonts/LexendDeca-Regular.ttf").toExternalForm(), 40));

        // Title
        guiItems.add(new ContentHolder(new Point(339, 60), new Image("/texts/you_won.png"), gc));

        // Icons
        guiItems.add(new ContentHolder(new Point(100, 322), new Image("/icons/leaf_left.png"), gc));
        guiItems.add(new ContentHolder(new Point(200, 275), new Image("/icons/trophy_big.png"), gc));
        guiItems.add(new ContentHolder(new Point(450, 322), new Image("/icons/leaf_right.png"), gc));

        // Scores
        guiItems.add(new ContentHolder(new Point(654, 276), new Image("/texts/current_highscore.png"), gc));
        highscoreLbl = new TextHolder(new Point(654, 366), "0", gc);
        guiItems.add(highscoreLbl);

        guiItems.add(new ContentHolder(new Point(654, 425), new Image("/texts/current_score.png"), gc));
        scoreLbl = new TextHolder(new Point(654, 515), "0", gc);
        guiItems.add(scoreLbl);

        // Buttons
        Button button_exit = CustomButton.createButton(new Image("/buttons/exit_big.png"), new Point(235, 640));
        button_exit.setOnAction(event -> System.exit(0));

        Button button_back = CustomButton.createButton(new Image("/buttons/back_to_menu.png"), new Point(615, 640));
        button_back.setOnAction(event -> nav.navigateTo(SceneType.START));

        pane.getChildren().addAll(button_exit, button_back);
    }

    @Override
    public void initialize() {


        int difficultyScore = 0;

        switch(difficulty){
            case EASY:
                difficultyScore = 20;
                break;

            case MEDIUM:
                difficultyScore = 80;
                break;

            case HARD:
                difficultyScore = 300;
                break;
        }

        //  Berechnung des Scores
        int score = (int) Math.round((double) bombCount / (double) gameTime * difficultyScore);

        if (score > FileManager.getHighscore(profile)){
            FileManager.setHighscore(profile, score);
        }

        highscoreLbl.setText(Integer.toString(FileManager.getHighscore(profile)));
        scoreLbl.setText(Integer.toString(score));

        drawScene();
    }

    @Override
    public void onNavigate(WinNavParams params) {
        difficulty = params.difficulty;
        profile = params.profile;
        bombCount = params.bombCount;
        gameTime = params.gameTime;
    }
}
