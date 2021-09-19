package ch.noebuerki.minesweeper.navigation.params;

import ch.noebuerki.minesweeper.enums.Profile;

public class LostNavParams extends NavParams{
    public Profile profile;

    public LostNavParams(Profile profile){

        this.profile = profile;
    }
}
