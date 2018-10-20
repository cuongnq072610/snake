package base.renderer;

import base.GameObject;
import base.counter.FrameCounter;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationRenderer extends Renderer {
    ArrayList<BufferedImage> images;
    int currentImage = 0;
    FrameCounter frameCounter;

    public AnimationRenderer(String ...urls) {
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(urls);
        this.images = images;
        this.frameCounter = new FrameCounter(5);
    }

    public AnimationRenderer(ArrayList<BufferedImage> images) {
        this.images = images;
        this.frameCounter = new FrameCounter(5);
    }

    public AnimationRenderer(ArrayList<BufferedImage> images, int frameCount) {
        this.images = images;
        this.frameCounter = new FrameCounter(frameCount);
    }

    @Override
    public void render(Graphics g, GameObject master) {
        if(images.size() > 0) {
            BufferedImage image = images.get(currentImage);
            double x = master.position.x - image.getWidth() * master.anchor.x;
            double y = master.position.y - image.getHeight() * master.anchor.y;
            g.drawImage(image, (int)x, (int)y, null);

            if(this.frameCounter.run()) {
                currentImage++;
                if(currentImage >= images.size() - 1) {
                    currentImage = 0;
                }
                this.frameCounter.reset();
            }
        }
    }
}
