package com.yp.tankGame;

import java.awt.*;
import java.util.Random;

public class Blood {
    public int x;
    public int y;
    public boolean isLive = false;
    public static final int width = 30;
    public static final int height = 30;

    Random r = new Random();
    private int step = 0;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] bloodImage = null;

    //存储图片
    static{
        bloodImage = new Image[]{
            tk.getImage(Blood.class.getResource("/images/blood.png")),
        };
    }

    private int[][]position={{300,400},{800,400},{300,150},{800,150}};

    public void drawBlood(Graphics g){
        if(r.nextInt(100)>98){
            this.isLive = true;
            move();
        }
        //不进行绘制
        if(isLive == false)
            return;//停止执行
        g.drawImage(bloodImage[0],x,y,30,30,null);
    }

    public void move(){
        step++;
        if(step == position.length){
            step = 0;
        }
        x = position[step][0];
        y = position[step][1];
    }

    public Blood(int x,int y){
        this.x = x;
        this.y = y;
    }
    public Blood(){}

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
