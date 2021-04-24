package ch.bbcag.minesweeper;

import ch.bbcag.minesweeper.enums.SceneType;
import ch.bbcag.minesweeper.navigation.Navigator;
import ch.bbcag.minesweeper.navigation.params.StartNavParams;
import ch.bbcag.minesweeper.scenes.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.*;
import javafx.stage.Stage;

import java.nio.file.DirectoryNotEmptyException;

public class App extends Application {

    private Stage root;
    private Navigator nav;
    private MediaPlayer mediaPlayer;
    private String backgroundSongPath = Launcher.class.getResource("/sounds/music.mp3").toExternalForm();

    public void start(Stage stage) {

        root = new Stage();

        root.getIcons().add(new Image("/icons/main.png"));
        root.setResizable(false);
        root.setTitle("Minesweeper");
        root.setWidth(Dimensions.WIDTH);
        root.setHeight(Dimensions.HEIGHT);

        nav = new Navigator(root);

        Media backgroundSong = new Media(backgroundSongPath);
        mediaPlayer = new MediaPlayer(backgroundSong);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        nav.registerScene(SceneType.START, new StartScene(nav));
        nav.registerScene(SceneType.GAME, new GameScene(nav));
        nav.registerScene(SceneType.WIN, new WinScene(nav));
        nav.registerScene(SceneType.LOSE, new LoseScene(nav));
        nav.registerScene(SceneType.HIGH, new HighscoreScene(nav));
        nav.registerScene(SceneType.STARTHELP, new HelpScene(nav,SceneType.START));
        nav.registerScene(SceneType.GAMEHELP, new HelpScene(nav, SceneType.GAME));

        nav.navigateToWithParams(SceneType.START, new StartNavParams(mediaPlayer));
    }
}
