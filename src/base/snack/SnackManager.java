package base.snack;

import base.GameObject;
import base.Settings;
import base.Vector2D;
import base.player.Player;
import base.player.SnakePart;
import tklibs.Mathx;

import java.util.ArrayList;
import java.util.Random;

public class SnackManager {
    boolean[][] map;
    Vector2D currentSnackPos;
    Random r;
    ArrayList<String> lastPlayerPosUpdate;

    public SnackManager() {
        this.map = new boolean[Settings.COL_COUNT][Settings.ROW_COUNT];
        for (int j = 0; j < Settings.ROW_COUNT; j++) {
            for (int i = 0; i < Settings.COL_COUNT; i++) {
                map[i][j] = true;
            }
        }
        this.currentSnackPos = new Vector2D(-1, -1);
        this.r = new Random();
        this.lastPlayerPosUpdate = new ArrayList<>();
    }

    public void spawnSnack() {
        String pos = this.randomPos();
        Snack newSnack = GameObject.recycle(Snack.class);
        this.mapPosWithWaySize(newSnack, pos);

    }

    private void mapPosWithWaySize(Snack newS, String str) {
        newS.position.set(str)
                .scaleThis(Settings.WAY_SIZE)
                .addThis(Settings.WAY_SIZE / 2, Settings.WAY_SIZE / 2)
        ;
    }

    private String randomPos() {
        ArrayList<String> points = new ArrayList<>();
        Vector2D temp = new Vector2D();
        for (int j = 0; j < Settings.ROW_COUNT; j++) {
            for (int i = 0; i < Settings.COL_COUNT; i++) {
                if (map[i][j]) {
                    points.add(temp.set(i, j).toString());
                }
            }
        }
        int index = this.r.nextInt(points.size());
      //  System.out.println(index+"ooo"+points.get(index));

        return points.get(index);
    }

    public void updatePlayerPos(Player player) {
        // clear last position
        for (String lastPos :lastPlayerPosUpdate) {
            this.setToMap(lastPos, true);
        }
        // update new position
        for (SnakePart part : player.parts){
            String pos = part.position.add(Settings.WAY_SIZE/2,Settings.WAY_SIZE/2).scale(1.0f/Settings.WAY_SIZE).toString();
            this.setToMap(pos,false);
            this.lastPlayerPosUpdate.add(pos);
        }
    }


    /**
     * set value to pos in map
     * @param pos (x:1;y:2)
     * @param value true/false
     */
    public void setToMap(String pos, boolean value) {
        Vector2D temp = new Vector2D();
        temp.set(pos);
        int i = (int)temp.x;
        int j = (int)temp.y;
        i = Mathx.clamp(i,0,Settings.COL_COUNT-1);
        j = Mathx.clamp(j,0,Settings.ROW_COUNT-1);
        map[i][j] = value;
    }
}
