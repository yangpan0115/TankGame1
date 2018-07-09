package com.yp.tankGame;

import java.awt.*;

public class Home {
    public int x;
    public int y;
    public boolean isLive = true;
    public static final int width = 40;
    public static final int height = 40;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] homeImage = null;

    //存储图片
    static{
        homeImage = new Image[]{
                tk.getImage(Home.class.getResource("/images/home1.png")),
        };
    }
    public Home(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Home(){}

    //画出家
    public void drawHome(Graphics g){
        g.drawImage(homeImage[0],x,y,40,40,null);
    }

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
