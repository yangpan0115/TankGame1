package com.yp.tankGame;

import java.awt.*;

public class Wall {
    public int x;
    public int y;
    public boolean isLive = true;

    public static final int width = 40;
    public static final int height = 40;

    public Wall(int x,int y){
        this.x = x;
        this.y = y;
    }
    public Wall(){}

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
