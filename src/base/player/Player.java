package base.player;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.counter.FrameCounter;
import base.event.KeyEventPress;
import base.scene.GameOverScene;
import base.scene.SceneManager;
import base.scene.SceneStage1;

import java.util.ArrayList;
import java.util.Set;

public class Player extends GameObject {
    public ArrayList<SnakePart> parts;
    SnakeHead head;
    FrameCounter moveCounter;
    int direction;
    SnakePart newPart;

    public Player() {
        super();
        this.moveCounter = new FrameCounter(8);
        this.position.set(190, 290);
        this.initParts();
        this.direction = Settings.UP;
        //this.newPart = GameObject.recycle(SnakePart.class);
    }

    private void initParts() {
        this.parts = new ArrayList<>();
        this.head = GameObject.recycle(SnakeHead.class);
        this.head.position.set(this.position);
        SnakePart tail = GameObject.recycle(SnakePart.class);
        tail.position.set(this.head.position.add(0, -Settings.WAY_SIZE));
        this.parts.add(this.head);
        this.parts.add(tail);
    }

    public ArrayList<Vector2D> listVectorParts() {
        ArrayList<Vector2D> listV = new ArrayList<>();
        for (int i = 0; i < parts.size(); i++) {
            listV.add(parts.get(i).position);
        }
        return listV;
    }

    FrameCounter newSceneCounter = new FrameCounter(10000);

    @Override
    public void run() {
        this.setDirection();
        if (this.moveCounter.run()) {
            this.move();
            this.setPartsPosition();
            this.moveCounter.reset();
        }
//        if (newSceneCounter.run()) {
//            System.out.println("new scene coming");
//            SceneManager.signNewScene(new SceneStage1());
//        }
        this.updatePosToSnackManager();
    }

    private void updatePosToSnackManager() {
        SceneManager.currentScene.sm.updatePlayerPos(this);
    }

    private void setDirection() {
        if (KeyEventPress.isUpPress && this.direction != Settings.DOWN && this.canTurnTo(Settings.UP)) {
            this.direction = Settings.UP;
        } else if (KeyEventPress.isDownPress && this.direction != Settings.UP && this.canTurnTo(Settings.DOWN)) {
            this.direction = Settings.DOWN;
        } else if (KeyEventPress.isLeftPress && this.direction != Settings.RIGHT && this.canTurnTo(Settings.LEFT)) {
            this.direction = Settings.LEFT;
        } else if (KeyEventPress.isRightPress && this.direction != Settings.LEFT && this.canTurnTo(Settings.RIGHT)) {
            this.direction = Settings.RIGHT;
        }
    }

    public boolean canTurnTo(int direction) {
        SnakePart head = this.head;
        SnakePart neck = this.parts.get(1);
        boolean inline = false;
        switch (direction) {
            case Settings.UP:
            case Settings.DOWN:
                //vertical
                inline = head.position.x == neck.position.x;
                break;
            case Settings.LEFT:
            case Settings.RIGHT:
                //horizontal
                inline = head.position.y == neck.position.y;
        }
        return !inline;
    }


    private void move() {
        int x = (int) this.position.x;
        int y = (int)this.position.y;
        switch (this.direction) {
            case Settings.UP: {
                y -= Settings.WAY_SIZE;
                if (y < 0) {
                    y += Settings.SCREEN_HEIGHT;
                }

                break;
            }
            case Settings.DOWN: {
                y += Settings.WAY_SIZE;
                if (y > Settings.SCREEN_HEIGHT) {
                    y -= Settings.SCREEN_HEIGHT;
                }
                break;
            }
            case Settings.LEFT: {
                x -= Settings.WAY_SIZE;
                if (x < 0){
                    x += Settings.SCREEN_WIDHT;
                }
                break;
            }
            case Settings.RIGHT: {
                x += Settings.WAY_SIZE;
                if (x > Settings.SCREEN_WIDHT){
                    x -= Settings.SCREEN_WIDHT;
                }
                break;
            }
        }
        this.position.set(x,y);
    }

    private void setPartsPosition() {
        if (this.newPart != null) {
            parts.add(1, this.newPart);
            this.newPart.position.set(this.head.position);
            this.newPart.reset();
            this.newPart = null;
        } else {
            for (int i = this.parts.size() - 1; i > 0; i--) {
                SnakePart part = this.parts.get(i);
                SnakePart prevPart = this.parts.get(i - 1);
                part.position.set(prevPart.position);
            }
        }
        this.head.position.set(this.position);
    }

    public void growUp() {
        this.newPart = GameObject.recycle(SnakePart.class);
        this.newPart.destroy();
    }

    public void hit() {
        this.destroy();
        SnakePart partSecond = this.parts.get(1);
        this.head.position.set(partSecond.position);
        this.parts.remove(partSecond);
        partSecond.destroy();

        for(SnakePart part: parts){
            part.isDead = true;
        }

        SceneManager.signNewScene(new GameOverScene());
    }
}
