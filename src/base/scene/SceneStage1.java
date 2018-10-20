package base.scene;

import base.Background;
import base.GameObject;
import base.player.Player;
import base.snack.Snack;

public class SceneStage1 extends Scene {
    @Override
    public void destroy() {
        GameObject.clearAll();
    }

    @Override
    public void init() {
        GameObject.recycle(Background.class);
        this.player = GameObject.recycle(Player.class);
        Snack snack = GameObject.recycle(Snack.class);
        snack.position.set(190, 450);
    }
}
