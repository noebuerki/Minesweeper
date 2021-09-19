package ch.noebuerki.minesweeper.navigation.params;

import ch.bbcag.minesweeper.enums.*;
import ch.noebuerki.minesweeper.enums.Difficulty;
import ch.noebuerki.minesweeper.enums.Profile;

public class GameNavParams extends NavParams{
    public Difficulty difficulty;
    public Profile profile;

    public GameNavParams(Difficulty difficulty, Profile profile){

        this.difficulty = difficulty;
        this.profile = profile;
    }
}
