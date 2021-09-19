package ch.noebuerki.minesweeper.navigation;

import ch.noebuerki.minesweeper.enums.SceneType;
import ch.noebuerki.minesweeper.navigation.params.NavParams;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Navigator {

    private Stage stage;
    private Map<SceneType, Scene> sceneMap;
    private Scene activeScene;

    public Navigator(Stage stage){

        this.stage = stage;
        sceneMap = new HashMap<>();
    }

    public void registerScene(SceneType type, Scene scene) {

        sceneMap.put(type, scene);
    }

    public <T extends NavParams> void navigateToWithParams(SceneType type, T params){

        activeScene = sceneMap.get(type);

        if(activeScene instanceof Parameterized){
            ((Parameterized)activeScene).onNavigate(params);
        }

        navigateTo(type);
    }

    public void navigateTo(SceneType type){

        activeScene = sceneMap.get(type);

        if(activeScene instanceof Initializable){
            ((Initializable)activeScene).initialize();
        }

        stage.setScene(activeScene);
        stage.show();
    }
}
