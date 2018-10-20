package base.snack;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.BoxColliderRenderer;

import java.awt.*;
import java.util.Random;

public class Snack extends GameObject implements Physics {
    BoxCollider collider;

    public Snack() {
        super();
        this.collider = new BoxCollider(Settings.WAY_SIZE - 6
                ,Settings.WAY_SIZE - 6);
        this.renderer = new BoxColliderRenderer(Color.yellow, true);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
