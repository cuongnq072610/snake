package base.wall;

import base.GameObject;
import base.Settings;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.BoxColliderRenderer;

import java.awt.*;

public class Wall extends GameObject implements Physics {
    BoxCollider collider;
    public Wall(){
        super();
        this.renderer = new BoxColliderRenderer(Color.WHITE,true);
        this.collider = new BoxCollider(Settings.WAY_SIZE
                ,Settings.WAY_SIZE);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }


}
