package base.scene;

import base.player.Player;
import base.snack.SnackManager;

public abstract class Scene {
    public Player player;
    public SnackManager sm;

    public abstract void destroy();

    public abstract void init();
}
