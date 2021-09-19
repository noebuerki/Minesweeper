package ch.noebuerki.minesweeper.scenes;

import ch.bbcag.minesweeper.guiitems.*;
import ch.noebuerki.minesweeper.enums.SceneType;
import ch.bbcag.minesweeper.navigation.*;
import ch.noebuerki.minesweeper.guiitems.ContentHolder;
import ch.noebuerki.minesweeper.guiitems.CustomButton;
import ch.noebuerki.minesweeper.guiitems.Point;
import ch.noebuerki.minesweeper.navigation.Navigator;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class HelpScene extends RootScene {

    public HelpScene(Navigator nav, SceneType target) {

        super(nav);

        // Title
        guiItems.add(new ContentHolder(new Point(50, 50), new Image("/texts/help.png"), gc));

        // Icons
        guiItems.add(new ContentHolder(new Point(1000, 50), new Image("/icons/help.png"), gc));

        // Texts
        guiItems.add(new ContentHolder(new Point(50, 230), new Image("/texts/help_text.png"), gc));
        guiItems.add(new ContentHolder(new Point(50, 755), new Image("/texts/credits.png"), gc));

        // Buttons
        Button back_button = CustomButton.createButton(new Image("/buttons/back.png"), new Point(1070, 730));
        back_button.setOnAction(event -> nav.navigateTo(target));

        pane.getChildren().add(back_button);

        drawScene();
    }
}
