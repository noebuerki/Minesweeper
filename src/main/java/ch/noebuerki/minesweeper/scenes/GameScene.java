package ch.noebuerki.minesweeper.scenes;

import ch.bbcag.minesweeper.guiitems.*;
import ch.bbcag.minesweeper.guiitems.playfield.*;
import ch.bbcag.minesweeper.enums.*;
import ch.bbcag.minesweeper.navigation.*;
import ch.bbcag.minesweeper.navigation.params.*;
import ch.noebuerki.minesweeper.enums.Difficulty;
import ch.noebuerki.minesweeper.enums.FieldContent;
import ch.noebuerki.minesweeper.enums.Profile;
import ch.noebuerki.minesweeper.enums.SceneType;
import ch.noebuerki.minesweeper.guiitems.*;
import ch.noebuerki.minesweeper.guiitems.playfield.Field;
import ch.noebuerki.minesweeper.guiitems.playfield.PlayfieldCreator;
import ch.noebuerki.minesweeper.navigation.CustomAnimationTimer;
import ch.noebuerki.minesweeper.navigation.Initializable;
import ch.noebuerki.minesweeper.navigation.Navigator;
import ch.noebuerki.minesweeper.navigation.Parameterized;
import ch.noebuerki.minesweeper.navigation.params.GameNavParams;
import ch.noebuerki.minesweeper.navigation.params.LostNavParams;
import ch.noebuerki.minesweeper.navigation.params.WinNavParams;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.List;

public class GameScene extends RootScene implements Initializable, Parameterized<GameNavParams> {

    private Difficulty difficulty;
    private Profile profile;
    private int gameTime;
    private List<Field> playField;
    private TextHolder timer;
    private TextHolder flagCounter;
    private CustomAnimationTimer animationTimer;
    private long lastClockUpdate = 0;

    public GameScene(Navigator nav) {

        super(nav);

        gc.setFont(Font.loadFont(getClass().getResource("/fonts/LexendDeca-Regular.ttf").toExternalForm(), 50));

        // Title
        guiItems.add(new ContentHolder(new Point(56, 30), new Image("/texts/title_small.png"), gc));

        // Icons
        guiItems.add(new ContentHolder(new Point(56, 314), new Image("/icons/flag.png"), gc));
        guiItems.add(new ContentHolder(new Point(56, 426), new Image("/icons/clock.png"), gc));

        //Texts
        flagCounter = new TextHolder(new Point(136, 360), "Hallo", gc);
        guiItems.add(flagCounter);

        timer = new TextHolder(new Point(136, 475), "Hallo", gc);
        guiItems.add(timer);

        // Buttons
        Button button_help = CustomButton.createButton(new Image("/buttons/help.png"), new Point(20, 730));
        button_help.setOnAction(event -> {
            if(Field.isGameRunning()) {
                animationTimer.stop();
                nav.navigateTo(SceneType.GAMEHELP);
            }
        });

        Button button_exit = CustomButton.createButton(new Image("/buttons/back.png"), new Point(90, 730));
        button_exit.setOnAction(event -> {
            if(Field.isGameRunning()) {
                animationTimer.stop();
                nav.navigateTo(SceneType.START);
            }
        });

        Button button_back = CustomButton.createButton(new Image("/buttons/exit_small.png"), new Point(220, 730));
        button_back.setOnAction(event -> {
            if(Field.isGameRunning()) {
                System.exit(0);
            }
        });

        pane.getChildren().addAll(button_help, button_exit, button_back);

        drawScene();

        this.setOnMouseClicked(this::onMouseClicked);
    }

    public void onMouseClicked(MouseEvent click) {

        mouseClick = new Point(click.getX(), click.getY());

        for (RootItem item : guiItems) {
            if (item instanceof Field) {
                ((Field) item).checkForClick(click);
            }
        }
    }

    public void update() {

        if (System.nanoTime() - lastClockUpdate > 1000000000 && Integer.parseInt(timer.getText()) < 9999) {
            timer.setText(Integer.toString(Integer.parseInt(timer.getText()) + 1));
            lastClockUpdate = System.nanoTime();
        }

        if (Field.isGameWon()) {
            Field.isGameRunning(false);
            gameTime = Integer.parseInt(timer.getText());
            animationTimer.stop();
            PauseTransition winDelay = new PauseTransition(Duration.seconds(1));
            winDelay.setOnFinished(event -> nav.navigateToWithParams(SceneType.WIN, new WinNavParams(difficulty, profile, PlayfieldCreator.getBombcounter(), gameTime)));
            winDelay.play();
        } else if (Field.isGameLost()){
            Field.isGameRunning(false);
            gameTime = Integer.parseInt(timer.getText());
            animationTimer.stop();
            for(Field field : playField){
                if (field.getContent() == FieldContent.BOMB){
                    field.checkUncover();
                    field.draw();
                }
            }
            PauseTransition loseDelay = new PauseTransition(Duration.seconds(5));
            loseDelay.setOnFinished(event -> nav.navigateToWithParams(SceneType.LOSE, new LostNavParams(profile)));
            loseDelay.play();
        }

        drawScene();
    }

    @Override
    public void initialize() {

        Field.isGameWon(false);
        Field.isGameLost(false);
        Field.isGameRunning(true);

        playField = PlayfieldCreator.createPlayfield(difficulty, gc, this);

        guiItems.addAll(playField);

        flagCounter.setText(Integer.toString(PlayfieldCreator.getBombcounter()));
        timer.setText("0");

        animationTimer = new CustomAnimationTimer() {
            @Override
            public void doHandle(double deltaInSec) {
                update();
            }
        };
        animationTimer.start();

        drawScene();
    }

    @Override
    public void onNavigate(GameNavParams params) {

        difficulty = params.difficulty;
        profile = params.profile;
    }

    public TextHolder getFlagCounter() {
        return flagCounter;
    }

}
