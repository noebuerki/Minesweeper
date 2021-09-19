package ch.noebuerki.minesweeper.scenes;

import ch.noebuerki.minesweeper.FileManager;
import ch.bbcag.minesweeper.guiitems.*;
import ch.bbcag.minesweeper.enums.*;
import ch.bbcag.minesweeper.navigation.*;
import ch.noebuerki.minesweeper.enums.SceneType;
import ch.noebuerki.minesweeper.navigation.params.LostNavParams;
import ch.noebuerki.minesweeper.enums.Profile;
import ch.noebuerki.minesweeper.guiitems.ContentHolder;
import ch.noebuerki.minesweeper.guiitems.CustomButton;
import ch.noebuerki.minesweeper.guiitems.Point;
import ch.noebuerki.minesweeper.guiitems.TextHolder;
import ch.noebuerki.minesweeper.navigation.Initializable;
import ch.noebuerki.minesweeper.navigation.Navigator;
import ch.noebuerki.minesweeper.navigation.Parameterized;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class LoseScene extends RootScene implements Initializable, Parameterized<LostNavParams> {

    private Profile profile;
    private TextHolder highscoreLbl;

    public LoseScene(Navigator nav) {

        super(nav);

        gc.setFont(Font.loadFont(getClass().getResource("/fonts/LexendDeca-Regular.ttf").toExternalForm(), 40));

        // Title
        guiItems.add(new ContentHolder(new Point(182, 60), new Image("/texts/maybe_next_time.png"), gc));

        // Icons
        guiItems.add(new ContentHolder(new Point(200, 275), new Image("/icons/dynamite.png"), gc));

        // Scores
        guiItems.add(new ContentHolder(new Point(654, 350), new Image("/texts/current_highscore.png"), gc));

        highscoreLbl = new TextHolder(new Point(654, 440), "0", gc);
        guiItems.add(highscoreLbl);

        // Buttons
        Button button_exit = CustomButton.createButton(new Image("/buttons/exit_big.png"), new Point(235, 640));
        button_exit.setOnAction(event -> System.exit(0));

        Button button_back = CustomButton.createButton(new Image("/buttons/back_to_menu.png"), new Point(615, 640));
        button_back.setOnAction(event -> nav.navigateTo(SceneType.START));

        pane.getChildren().addAll(button_exit, button_back);
    }

    @Override
    public void initialize() {

        highscoreLbl.setText(Integer.toString(FileManager.getHighscore(profile)));

        drawScene();
    }

    @Override
    public void onNavigate(LostNavParams params) {
        profile = params.profile;
    }
}
