package base.player;

import base.GameObject;
import base.Settings;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.BoxColliderRenderer;

import javax.swing.*;
import java.awt.*;

public class SnakePart extends GameObject implements Physics {
    BoxCollider collider;

    public SnakePart() {
        super();
        this.collider = new BoxCollider(Settings.WAY_SIZE - 6
                , Settings.WAY_SIZE - 6);
        this.renderer = new BoxColliderRenderer(Color.WHITE);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return collider;
    }
}
