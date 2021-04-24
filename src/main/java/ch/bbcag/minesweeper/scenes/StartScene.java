package ch.bbcag.minesweeper.scenes;

import ch.bbcag.minesweeper.guiitems.*;
import ch.bbcag.minesweeper.enums.*;
import ch.bbcag.minesweeper.navigation.*;
import ch.bbcag.minesweeper.navigation.params.*;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.media.MediaPlayer;

public class StartScene extends RootScene implements Parameterized<StartNavParams> {
    private MediaPlayer mediaPlayer;
    private boolean isMusicPlaying = true;
    private Difficulty difficulty;
    private Profile profile;

    private Button button_profile_one, button_profile_two, button_profile_three, button_difficulty_easy, button_difficulty_medium, button_difficulty_hard;

    public StartScene(Navigator nav) {

        super(nav);

        // Title
        guiItems.add(new ContentHolder(new Point(281, 60), new Image("/texts/title_big.png"), gc));

        // Profile buttons
        guiItems.add(new ContentHolder(new Point(167, 250), new Image("/texts/choose_profile.png"), gc));

        button_profile_one = CustomButton.createButton(new Image("/buttons/profileone_notselected.png"), new Point(253, 345));
        button_profile_one.setOnAction(event -> {
            profile = Profile.ONE;
            setProfileImages(Profile.ONE);
        });

        button_profile_two = CustomButton.createButton(new Image("/buttons/profiletwo_notselected.png"), new Point(253, 446));
        button_profile_two.setOnAction(event -> {
            profile = Profile.TWO;
            setProfileImages(Profile.TWO);
        });

        button_profile_three = CustomButton.createButton(new Image("/buttons/profilethree_notselected.png"), new Point(253, 546));
        button_profile_three.setOnAction(event -> {
            profile = Profile.THREE;
            setProfileImages(Profile.THREE);
        });

        // Difficulty buttons
        guiItems.add(new ContentHolder(new Point(648, 250), new Image("/texts/select_difficulty.png"), gc));

        button_difficulty_easy = CustomButton.createButton(new Image("/buttons/easy_notselected.png"), new Point(726, 345));
        button_difficulty_easy.setOnAction(event -> {
            difficulty = Difficulty.EASY;
            SetDifficultyImages(Difficulty.EASY);
        });

        button_difficulty_medium = CustomButton.createButton(new Image("/buttons/medium_notselected.png"), new Point(726, 446));
        button_difficulty_medium.setOnAction(event -> {
            difficulty = Difficulty.MEDIUM;
            SetDifficultyImages(Difficulty.MEDIUM);
        });

        button_difficulty_hard = CustomButton.createButton(new Image("/buttons/hard_notselected.png"), new Point(726, 546));
        button_difficulty_hard.setOnAction(event -> {
            difficulty = Difficulty.HARD;
            SetDifficultyImages(Difficulty.HARD);
        });

        // Function buttons
        Button button_start = CustomButton.createButton(new Image("/buttons/start.png"), new Point(560, 650));
        button_start.setOnAction(event -> {
            if(difficulty != null && profile != null){
                nav.navigateToWithParams(SceneType.GAME, new GameNavParams(difficulty, profile));
            }
        });

        Button button_help = CustomButton.createButton(new Image("/buttons/help.png"), new Point(20, 730));
        button_help.setOnAction(event -> nav.navigateTo(SceneType.STARTHELP));

        Button button_highscore = CustomButton.createButton(new Image("/buttons/highscore.png"), new Point(90, 730));
        button_highscore.setOnAction(event -> nav.navigateTo(SceneType.HIGH));

        Button button_sound = CustomButton.createButton(new Image("/buttons/sound_active.png"), new Point(1050, 730));
        button_sound.setOnAction(event -> {
            if (isMusicPlaying){
                isMusicPlaying = false;
                button_sound.setGraphic(new ImageView(new Image("/buttons/sound_notactive.png")));
                mediaPlayer.stop();
            } else {
                isMusicPlaying = true;
                button_sound.setGraphic(new ImageView(new Image("/buttons/sound_active.png")));
                mediaPlayer.play();
            }
        });

        Button button_exit = CustomButton.createButton(new Image("/buttons/exit_door.png"), new Point(1130, 730));
        button_exit.setOnAction(event -> System.exit(0));

        pane.getChildren().addAll(button_profile_one, button_profile_two, button_profile_three, button_difficulty_easy, button_difficulty_medium, button_difficulty_hard, button_start, button_help, button_highscore, button_sound, button_exit);

        drawScene();
    }

    private void setProfileImages(Profile clicked){
        switch (clicked) {
            case ONE:
                button_profile_one.setGraphic(new ImageView(new Image("/buttons/profileone_selected.png")));
                button_profile_two.setGraphic(new ImageView(new Image("/buttons/profiletwo_notselected.png")));
                button_profile_three.setGraphic(new ImageView(new Image("/buttons/profilethree_notselected.png")));
                break;
            case TWO:
                button_profile_one.setGraphic(new ImageView(new Image("/buttons/profileone_notselected.png")));
                button_profile_two.setGraphic(new ImageView(new Image("/buttons/profiletwo_selected.png")));
                button_profile_three.setGraphic(new ImageView(new Image("/buttons/profilethree_notselected.png")));
                break;
            case THREE:
                button_profile_one.setGraphic(new ImageView(new Image("/buttons/profileone_notselected.png")));
                button_profile_two.setGraphic(new ImageView(new Image("/buttons/profiletwo_notselected.png")));
                button_profile_three.setGraphic(new ImageView(new Image("/buttons/profilethree_selected.png")));
                break;
        }
    }

    private void SetDifficultyImages(Difficulty clicked){
        switch (clicked) {
            case EASY:
                button_difficulty_easy.setGraphic(new ImageView(new Image("/buttons/easy_selected.png")));
                button_difficulty_medium.setGraphic(new ImageView(new Image("/buttons/medium_notselected.png")));
                button_difficulty_hard.setGraphic(new ImageView(new Image("/buttons/hard_notselected.png")));
                break;
            case MEDIUM:
                button_difficulty_easy.setGraphic(new ImageView(new Image("/buttons/easy_notselected.png")));
                button_difficulty_medium.setGraphic(new ImageView(new Image("/buttons/medium_selected.png")));
                button_difficulty_hard.setGraphic(new ImageView(new Image("/buttons/hard_notselected.png")));
                break;
            case HARD:
                button_difficulty_easy.setGraphic(new ImageView(new Image("/buttons/easy_notselected.png")));
                button_difficulty_medium.setGraphic(new ImageView(new Image("/buttons/medium_notselected.png")));
                button_difficulty_hard.setGraphic(new ImageView(new Image("/buttons/hard_selected.png")));
                break;
        }
    }

    @Override
    public void onNavigate(StartNavParams params) {
        mediaPlayer = params.mediaPlayer;
    }
}
