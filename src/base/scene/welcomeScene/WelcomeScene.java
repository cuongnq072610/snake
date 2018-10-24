package base.scene.welcomeScene;

import base.GameObject;
import base.renderer.SingleImageRenderer;
import base.scene.Scene;
import tklibs.SpriteUtils;

public class WelcomeScene extends Scene {
    @Override
    public void destroy() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        GameObject.recycle(Banner.class);

    }
}
