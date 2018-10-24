package game;

import base.*;
import base.scene.SceneManager;
import base.scene.welcomeScene.WelcomeScene;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    public GameCanvas() {
        SceneManager.signNewScene(new WelcomeScene());
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
