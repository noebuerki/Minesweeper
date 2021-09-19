package ch.noebuerki.minesweeper.navigation;

import ch.noebuerki.minesweeper.navigation.params.NavParams;

public interface Parameterized<T extends NavParams> {

    void onNavigate(T params);
}
