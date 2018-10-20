package base.scene;

import base.player.Player;

public abstract class Scene {
    public Player player;

    public abstract void destroy();

    public abstract void init();
}
