package com.yp.tankGame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//敌方坦克
//需要做成线程类
public class EnemyTank extends Tank {
    Random r = new Random();
    private int step = r.nextInt(10)+5;//产生一个随机数，模拟坦克的移动路径

    public EnemyTank(int x,int y,Direction direction){
        super(x,y,direction);
    }
    public EnemyTank(){}
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] enemyTankImage = null;
    private static Map<Integer, Image> enemyTankimgs = new HashMap<Integer, Image>();

    static{
        enemyTankImage = new Image[]{
                tk.getImage(EnemyTank.class.getResource("/images/enemytank_U.png")),
                tk.getImage(EnemyTank.class.getResource("/images/enemytank_D.png")),
                tk.getImage(EnemyTank.class.getResource("/images/enemytank_L.png")),
                tk.getImage(EnemyTank.class.getResource("/images/enemytank_R.png")),
        };
        enemyTankimgs.put(0,enemyTankImage[0]);
        enemyTankimgs.put(1,enemyTankImage[1]);
        enemyTankimgs.put(2,enemyTankImage[2]);
        enemyTankimgs.put(3,enemyTankImage[3]);
    }

    //画坦克
    public void drawEnemytank(int x, int y, Graphics g, Direction direction){
        switch (direction) {
            case U:
                // 向上
                g.drawImage(enemyTankimgs.get(0),x,y,40,40,null);
                break;
            case D:
                // 向下
                g.drawImage(enemyTankimgs.get(1),x,y,40,40,null);
                break;
            case L:
                // 向左
                g.drawImage(enemyTankimgs.get(2),x,y,40,40,null);
                break;
            case R:
                // 向右
                g.drawImage(enemyTankimgs.get(3),x,y,40,40,null);
                break;
            default:
                break;
        }
        flag = true;
        move();
        NewDirection();
        bflag = true;
        if(bullets.size()<1)
        bulletShot();
    }

    public void NewDirection(){
        if(step==0){
            step = r.nextInt(10)+3;
            Direction[] directions = Direction.values();
            int dir = r.nextInt(directions.length);
            direction = directions[dir];
        }
        step--;
    }
    //画生命值
    public void drawLife(Graphics g){
        g.setColor(Color.black);
        g.drawRect(x,y-20,width,10);
        int w = width * life/100;
        g.fillRect(x,y-20,w,10);
    }
}
