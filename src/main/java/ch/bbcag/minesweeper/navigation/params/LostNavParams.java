package ch.bbcag.minesweeper.navigation.params;

import ch.bbcag.minesweeper.enums.Profile;

public class LostNavParams extends NavParams{
    public Profile profile;

    public LostNavParams(Profile profile){

        this.profile = profile;
    }
}
