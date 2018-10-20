package base;

import java.awt.*;

public class Background extends GameObject {
    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        for(int i = 0; i < Settings.COL_COUNT; i++) {
            g.drawLine(i * Settings.WAY_SIZE, 0
                    , i * Settings.WAY_SIZE, Settings.SCREEN_HEIGHT);
        }
        for(int i = 0; i < Settings.ROW_COUNT; i++) {
            g.drawLine(0, i * Settings.WAY_SIZE
                    , Settings.SCREEN_WIDHT, i * Settings.WAY_SIZE);
        }
    }
}
