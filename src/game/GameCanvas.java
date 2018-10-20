package game;

import base.*;
import base.player.Player;
import base.player.SnakePart;
import base.renderer.BoxColliderRenderer;
import base.scene.SceneManager;
import base.scene.SceneStage1;
import base.snack.Snack;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    public GameCanvas() {
        SceneManager.signNewScene(new SceneStage1());
    }

    public void run() {
        GameObject.runAll();
    }

    public void render() {
        GameObject.renderAllToBackBuffer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        GameObject.renderBackBufferToGame(g);
    }
}
