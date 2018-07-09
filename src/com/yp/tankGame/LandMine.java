package com.yp.tankGame;

import java.awt.*;

//地雷
public class LandMine {
    public int x;
    public int y;
    public boolean isLive = true;
    public static final int width = 30;
    public static final int height = 30;


    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] landMineImage = null;

    //存储图片
    static{
        landMineImage = new Image[]{
                tk.getImage(LandMine.class.getResource("/images/landmine.png")),
        };
    }

    public void drawLandMine(Graphics g){
        g.drawImage(landMineImage[0],x,y,30,30,null);
    }

    public LandMine(int x,int y){
        this.x = x;
        this.y = y;
    }
    public LandMine(){}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }
}
