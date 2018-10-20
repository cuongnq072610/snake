package base.renderer;

import base.GameObject;
import base.physics.BoxCollider;
import base.physics.Physics;

import java.awt.*;

public class BoxColliderRenderer extends Renderer {
    Color color;
    boolean isFill;

    public BoxColliderRenderer(Color color) {
        this(color, false);
    }

    public BoxColliderRenderer(Color color, boolean isFill) {
        this.color = color;
        this.isFill = isFill;
    }

    @Override
    public void render(Graphics g, GameObject master) {
        if(master instanceof Physics) {
            g.setColor(this.color);
            BoxCollider boxCollider = ((Physics)master).getBoxCollider();
            if(this.isFill) {
                g.fillRect(boxCollider.left(master), boxCollider.top(master),
                        boxCollider.width, boxCollider.height);
            } else {
                g.drawRect(boxCollider.left(master), boxCollider.top(master),
                        boxCollider.width, boxCollider.height);
            }
        }
    }
}
