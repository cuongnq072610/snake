package base.player;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.renderer.BoxColliderRenderer;
import base.scene.SceneManager;
import base.snack.Snack;
import base.wall.Wall;
import game.GameCanvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeHead extends SnakePart {
    public SnakeHead() {
        super();
        this.renderer = new BoxColliderRenderer(Color.green);
    }

    @Override
    public void run() {
        this.checkIntersects();
    }
    public void spawn(ArrayList<Vector2D> list){
        Random r = new Random();
        boolean flag = true;
        Snack snack = GameObject.recycle(Snack.class);
        Vector2D v = new Vector2D(Settings.WAY_SIZE*r.nextInt(Settings.COL_COUNT)-10,Settings.WAY_SIZE*r.nextInt(Settings.ROW_COUNT)-10);
        if (v.x < 0){
            v.x = 0;
        }
        if (v.y < 0){
            v.y = 0;
        }
        for (Vector2D vec : list) {
            if (vec.x == v.x && vec.y == v.y) {
                v = new Vector2D(Settings.WAY_SIZE*r.nextInt(Settings.COL_COUNT)-10,Settings.WAY_SIZE*r.nextInt(Settings.ROW_COUNT)-10);
            }
        }
        snack.position.set(v);
    }
    private void checkIntersects() {
        //intersect with snake's parts
        Wall wall = GameObject.intersect(Wall.class,this);
        if(wall!=null){
            SceneManager.currentScene.player.hit();
        }
        SnakePart part = GameObject.intersect(SnakePart.class, this);
        // the part isn't the second part
        if(part!=null && SceneManager.currentScene.player.parts.indexOf(part) != 1){
            SceneManager.currentScene.player.hit();
        }
        //intersect with food
        Snack snack = GameObject.intersect(Snack.class, this);
        if(snack != null) {
            //ArrayList<Vector2D> list = SceneManager.currentScene.player.listVectorParts();
            snack.destroy();
            //spawn(list);
            SceneManager.currentScene.player.growUp();
            SceneManager.currentScene.sm.spawnSnack();

        }
    }
}
