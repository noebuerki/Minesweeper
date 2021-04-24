package ch.bbcag.minesweeper.navigation.params;

import ch.bbcag.minesweeper.enums.*;

public class WinNavParams extends NavParams{
    public Difficulty difficulty;
    public Profile profile;
    public int bombCount;
    public int gameTime;

    public WinNavParams(Difficulty difficulty, Profile profile, int bombCount, int gameTime){

        this.difficulty = difficulty;
        this.profile = profile;
        this.bombCount = bombCount;
        this.gameTime = gameTime;
    }
}
