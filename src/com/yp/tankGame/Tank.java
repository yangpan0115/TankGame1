package com.yp.tankGame;

import java.awt.*;
import java.util.Vector;

public class Tank {
    public int x;
    public int y;
    public int speed = 5;
    public Direction direction = Direction.U;//初始化方向向上
    public boolean isLive = true;
    public int life = 100;
    private int oldX;
    private int oldY;
    public boolean flag = false;
    public boolean bflag = false;

    public static final int width = 40;
    public static final int height = 40;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
    }

    public Tank(int x, int y, Direction direction) {
        this(x,y);
        this.direction = direction;
    }

    public Tank(){}

    //生命值减少
    public void lifeDown(){
        if(life > 0){
            life -= 20;
        }
        if(life == 0)
            isLive = false;

    }

    public void move(){
        this.oldX = x;
        this.oldY = y;
        if(flag == true){
            switch (direction){
                case U:
                    y -= speed;
                    break;
                case D:
                    y += speed;
                    break;
                case L:
                    x -= speed;
                    break;
                case R:
                    x += speed;
                    break;
                default:
                    break;
            }
        }
        if(x<0)
            x = 0;
        if(y<20)
            y = 20;
        if(x + Tank.width > MyPanel.PanelWidth)
            x = MyPanel.PanelWidth - Tank.width;
        if(y + Tank.height > MyPanel.PanelHeight)
            y = MyPanel.PanelHeight - Tank.height;
    }


    public void stop(){
        x = oldX;
        y = oldY;
    }

    //子弹集合
    Vector<Bullet> bullets = new Vector<Bullet>();
    Bullet bullet = null;

    //装填子弹
    public void bulletShot(){
        if(bflag == true){
            switch(direction){
                case U:
                    bullet = new Bullet(x+15,y-15, Direction.U);
                    bullets.add(bullet);
                    break;
                case D:
                    bullet = new Bullet(x+13,y+40, Direction.D);
                    bullets.add(bullet);
                    break;
                case L:
                    bullet = new Bullet(x-15,y+17, Direction.L);
                    bullets.add(bullet);
                    break;
                case R:
                    bullet = new Bullet(x+40,y+17, Direction.R);
                    bullets.add(bullet);
                    break;
                default:
                    break;
            }
        }
    }

    //吃血包
    public boolean getBlood(Blood blood){
        if(this.getRect().intersects(blood.getRect())){
            blood.isLive = false;
            if(this.life == 100){
                this.life += 0;
            }else{
                this.life = 100;
            }
            return true;
        }
        return false;
    }

    //碰撞检测
    //1 普通墙
    public boolean collideCommonWall(CommonWall cw){
        if(this.isLive&&this.getRect().intersects(cw.getRect())){
            this.stop();
            return true;
        }
        return false;
    }
    //2 金属墙
    public boolean collideMetalWall(MetalWall mw){
        if(this.isLive&&this.getRect().intersects(mw.getRect())){
            this.stop();
            return true;
        }
        return false;
    }
    //3 家
    public boolean collideHome(Home home){
        if(this.isLive&&this.getRect().intersects(home.getRect())){
            this.stop();
            return true;
        }
        return false;
    }
    //4 其他坦克
    public boolean collideTank(Tank tank){
        if(this != tank){
            if(this.isLive&&this.getRect().intersects(tank.getRect())){
                this.stop();
                tank.stop();
                return true;
            }
        }
        return false;
    }

    //5地雷
    public boolean collideLandMine(LandMine landMine){
        if(this.isLive&&this.getRect().intersects(landMine.getRect())){
            landMine.isLive = false;
            this.isLive = false;
            return true;
        }
        return false;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }
}
