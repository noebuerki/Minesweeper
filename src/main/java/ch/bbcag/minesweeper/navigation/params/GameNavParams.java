package ch.bbcag.minesweeper.navigation.params;

import ch.bbcag.minesweeper.enums.*;

public class GameNavParams extends NavParams{
    public Difficulty difficulty;
    public Profile profile;

    public GameNavParams(Difficulty difficulty, Profile profile){

        this.difficulty = difficulty;
        this.profile = profile;
    }
}
