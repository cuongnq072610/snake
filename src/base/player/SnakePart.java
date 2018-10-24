package base.player;

import base.GameObject;
import base.Settings;
import base.counter.FrameCounter;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.BoxColliderRenderer;

import javax.swing.*;
import java.awt.*;

public class SnakePart extends GameObject implements Physics {
    BoxCollider collider;
    boolean isDead;
    FrameCounter blinkCounter;


    public SnakePart() {
        super();
        this.collider = new BoxCollider(Settings.WAY_SIZE - 6
                , Settings.WAY_SIZE - 6);
        this.renderer = new BoxColliderRenderer(Color.WHITE);
        isDead = false;
        this.blinkCounter = new FrameCounter(4);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return collider;
    }

    @Override
    public void render(Graphics g) {
        if (this.isDead) {
            if (this.blinkCounter.run()) {
                super.render(g);
                this.blinkCounter.reset();
            }
        } else {
            super.render(g);
        }
    }
}
