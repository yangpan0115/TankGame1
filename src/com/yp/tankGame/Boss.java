package com.yp.tankGame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class Boss extends Tank {
    Random r = new Random();
    private int step = r.nextInt(10)+5;//产生一个随机数，模拟坦克的移动路径

    public Boss(int x,int y,Direction direction){
        super(x,y,direction);
    }
    public Boss(){}
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] BossImage = null;
    private static Map<Integer, Image> Bossimgs = new HashMap<Integer, Image>();

    static{
        BossImage = new Image[]{
                tk.getImage(EnemyTank.class.getResource("/images/boss_U.png")),
                tk.getImage(EnemyTank.class.getResource("/images/boss_D.png")),
                tk.getImage(EnemyTank.class.getResource("/images/boss_L.png")),
                tk.getImage(EnemyTank.class.getResource("/images/boss_R.png")),
        };
        Bossimgs.put(0,BossImage[0]);
        Bossimgs.put(1,BossImage[1]);
        Bossimgs.put(2,BossImage[2]);
        Bossimgs.put(3,BossImage[3]);
    }

    //画坦克
    public void drawBoss(int x, int y, Graphics g, Direction direction){
        switch (direction) {
            case U:
                // 向下
                g.drawImage(Bossimgs.get(0),x,y,40,40,null);
                break;
            case D:
                // 向下
                g.drawImage(Bossimgs.get(1),x,y,40,40,null);
                break;
            case L:
                // 向左
                g.drawImage(Bossimgs.get(2),x,y,40,40,null);
                break;
            case R:
                // 向右
                g.drawImage(Bossimgs.get(3),x,y,40,40,null);
                break;
            default:
                break;
        }
        flag = true;
        move();
        NewDirection();
        bflag = true;
        if(bullets.size()<1)
            bossShot();
    }

    public void NewDirection(){
        if(step==0){
            step = r.nextInt(10)+20;
            Direction[] directions = Direction.values();
            int dir = r.nextInt(directions.length);
            direction = directions[dir];
        }
        step--;
    }
    //画生命值
    public void drawLife(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.black);
        g.drawRect(x,y-20,width,10);
        int w = width * life/100;
        g.fillRect(x,y-20,w,10);
        g.setColor(c);

    }

    //子弹集合
    Vector<Bullet> bullets = new Vector<Bullet>();
    Bullet bullet1 = null;
    Bullet bullet2 = null;
    //开火
    public void bossShot(){
        if(bflag == true){
            switch(direction){
                case U:
                    bullet1 = new Bullet(x+6,y-10, Direction.U);
                    bullets.add(bullet1);
                    bullet2 = new Bullet(x+25,y-10, Direction.U);
                    bullets.add(bullet2);
                    break;
                case D:
                    bullet1 = new Bullet(x+4,y+40, Direction.D);
                    bullets.add(bullet1);
                    bullet2 = new Bullet(x+22,y+40, Direction.D);
                    bullets.add(bullet2);
                    break;
                case L:
                    bullet1 = new Bullet(x-10,y+7, Direction.L);
                    bullets.add(bullet1);
                    bullet2 = new Bullet(x-5,y+27, Direction.L);
                    bullets.add(bullet2);
                    break;
                case R:
                    bullet1 = new Bullet(x+40,y+9, Direction.R);
                    bullets.add(bullet1);
                    bullet2 = new Bullet(x+36,y+27, Direction.R);
                    bullets.add(bullet2);
                    break;
                default:
                    break;
            }
        }
    }
}
