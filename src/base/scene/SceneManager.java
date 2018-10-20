package base.scene;

public class SceneManager {
    public static Scene currentScene;
    public static Scene nextScene;

    public static void signNewScene(Scene scene) {
        nextScene = scene;
    }

    public static void changeSceneIfNeeded() {
        if(nextScene != null) {
            if(currentScene != null) {
                currentScene.destroy();
            }
            nextScene.init();
            currentScene = nextScene;
            nextScene = null;
        }
    }
}
