package base.action;

import base.GameObject;

public abstract class Action {
    public boolean isDone;
    public abstract void run(GameObject master);
    public abstract void reset();
}
