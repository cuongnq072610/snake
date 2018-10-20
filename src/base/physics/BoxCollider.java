package base.physics;

import base.GameObject;

public class BoxCollider {
    public int width;
    public int height;

    public BoxCollider(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int top(GameObject master) {
        return (int) (master.position.y - master.anchor.x * this.height);
    }

    public int bot(GameObject master) {
        return (int) this.top(master) + height;
    }

    public int left(GameObject master) {
        return (int) (master.position.x - master.anchor.y * this.width);
    }

    public int right(GameObject master) {
        return (int) (this.left(master) + this.width);
    }

    public boolean intersect(Physics other, GameObject master) {
        //oM == other Master
        //oB == other BoxCollider
        GameObject oM = (GameObject) other;
        BoxCollider oB = other.getBoxCollider();

        boolean yIntersect = (this.top(master) >= oB.top(oM) && this.top(master) < oB.bot(oM))
                || (this.bot(master) >= oB.top(oM) && this.bot(master) < oB.bot(oM));
        boolean xIntersect = (this.right(master) >= oB.left(oM) && this.right(master) < oB.right(oM))
                || (this.left(master) >= oB.left(oM) && this.left(master) < oB.right(oM));
        return yIntersect && xIntersect;
    }
}
