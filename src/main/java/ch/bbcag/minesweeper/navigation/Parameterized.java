package ch.bbcag.minesweeper.navigation;

import ch.bbcag.minesweeper.navigation.params.NavParams;

public interface Parameterized<T extends NavParams> {

    void onNavigate(T params);
}
