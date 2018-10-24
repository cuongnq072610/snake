package base.scene;

import base.GameObject;
import base.scene.gameOverScene.Banner;

public class GameOverScene extends Scene {
    @Override
    public void destroy() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        GameObject.recycle(Banner.class);
    }
}
