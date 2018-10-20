package base.action;

import base.GameObject;
import base.counter.FrameCounter;

public class ActionWait extends Action {
    FrameCounter counter;
    public ActionWait(int frame) {
        this.isDone = false;
        this.counter = new FrameCounter(frame);
    }

    @Override
    public void run(GameObject master) {
        if(this.counter.run()) {
            this.isDone = true;
        }
    }

    @Override
    public void reset() {
        this.counter.reset();
        this.isDone = false;
    }
}
