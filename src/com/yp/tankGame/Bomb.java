package com.yp.tankGame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Bomb {
    int x;
    int y;
    //炸弹的生命
    int life = 5;
    boolean isLive = true;

    public Bomb(int x,int y){
        this.x = x;
        this.y = y;
    }
    public Bomb(){}

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] bombImage = null;
    private static Map<Integer, Image> bombimgs = new HashMap<Integer, Image>();
    static{
        bombImage = new Image[]{
                tk.getImage(Bomb.class.getResource("/images/bomb_1.png")),
                tk.getImage(Bomb.class.getResource("/images/bomb_2.png")),
                tk.getImage(Bomb.class.getResource("/images/bomb_3.png")),
                tk.getImage(Bomb.class.getResource("/images/bomb_4.png")),
                tk.getImage(Bomb.class.getResource("/images/bomb_5.png")),
        };
        bombimgs.put(0,bombImage[0]);
        bombimgs.put(1,bombImage[1]);
        bombimgs.put(2,bombImage[2]);
        bombimgs.put(3,bombImage[3]);
        bombimgs.put(4,bombImage[4]);
    }
    public void drawBomb(Graphics g){
        if (this.life > 4) {
            g.drawImage(bombimgs.get(0), x, y, null);
        } else if (this.life > 3) {
            g.drawImage(bombimgs.get(1), x, y,  null);
        } else if (this.life > 2) {
            g.drawImage(bombimgs.get(2), x, y,  null);
        } else if (this.life > 1) {
            g.drawImage(bombimgs.get(3), x, y,  null);
        } else if (this.life > 0) {
            g.drawImage(bombimgs.get(4), x, y,  null);
        }
        this.lifeDown();
    }

    //减少生命值
    public void lifeDown(){
        if(life > 0){
            life--;
        }else{
            isLive = false;
        }
    }
}
