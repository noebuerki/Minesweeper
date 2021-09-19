package ch.noebuerki.minesweeper.navigation.params;

import javafx.scene.media.MediaPlayer;

public class StartNavParams extends NavParams{
    public MediaPlayer mediaPlayer;

    public StartNavParams(MediaPlayer mediaPlayer){

        this.mediaPlayer = mediaPlayer;
    }
}
