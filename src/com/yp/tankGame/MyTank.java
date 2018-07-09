package com.yp.tankGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MyTank extends Tank {
    public MyTank(int x,int y,Direction direction){
        super(x,y,direction);
    }
    public MyTank(){}
    private static Toolkit tk = Toolkit.getDefaultToolkit();//拿到默认工具包，通过工具包的方法把硬盘上的图片拿到内存中来
    private static Image[] myTankImage = null;
    private static Map<Integer, Image> myTankimgs = new HashMap<Integer, Image>();

    static{//静态代码区， “静态块”中代码的特点是：在这个类被装载时就会执行，且只执行一次。
        myTankImage = new Image[]{
                tk.getImage(MyTank.class.getResource("/images/mytank_U.png")),
                tk.getImage(MyTank.class.getResource("/images/mytank_D.png")),
                tk.getImage(MyTank.class.getResource("/images/mytank_L.png")),
                tk.getImage(MyTank.class.getResource("/images/mytank_R.png")),
        };
        myTankimgs.put(0,myTankImage[0]);
        myTankimgs.put(1,myTankImage[1]);
        myTankimgs.put(2,myTankImage[2]);
        myTankimgs.put(3,myTankImage[3]);
    }
    //画坦克
    public void drawMytank(int x, int y, Graphics g, Direction direction){
        switch (direction) {
            case U:
                // 向上
                g.drawImage(myTankimgs.get(0),x,y,40,40,null);
                break;
            case D:
                // 向下
                g.drawImage(myTankimgs.get(1),x,y,40,40,null);
                break;
            case L:
                // 向左
                g.drawImage(myTankimgs.get(2),x,y,40,40,null);
                break;
            case R:
                // 向右
                g.drawImage(myTankimgs.get(3),x,y,40,40,null);
                break;
            default:
                break;
        }
        move();
    }
    //画生命值
    public void drawLife(Graphics g){
        g.setColor(Color.yellow);
        g.drawRect(x,y-20,width,10);
        int w = MyTank.width * life/100;
        g.fillRect(x,y-20,w,10);
    }
    //画基地生命值
    public void drawHomeLife(Graphics g){
        g.setColor(Color.yellow);
        g.drawRect(500,525,width,10);
        int w = width * life/100;
        g.fillRect(500,525,w,10);
    }


    //导弹集合
    Vector<Missile> missiles = new Vector<Missile>();
    Missile missile = null;
    //装填导弹
    public void missileShot(){
        if(bflag == true){
            switch(direction){
                case U:
                    missile = new Missile(x+6,y-20, Direction.U);
                    missiles.add(missile);
                    break;
                case D:
                    missile = new Missile(x+8,y+40, Direction.D);
                    missiles.add(missile);
                    break;
                case L:
                    missile = new Missile(x-15,y+10, Direction.L);
                    missiles.add(missile);
                    break;
                case R:
                    missile = new Missile(x+40,y+8, Direction.R);
                    missiles.add(missile);
                    break;
                default:
                    break;
            }
            lifeDown();
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:{
                flag = true;
                direction = Direction.U;
                break;
            }
            case KeyEvent.VK_DOWN:{
                flag = true;
                direction = Direction.D;
                break;
            }
            case KeyEvent.VK_LEFT:{
                flag = true;
                direction = Direction.L;
                break;
            }
            case KeyEvent.VK_RIGHT:{
                flag = true;
                direction = Direction.R;
                break;
            }
            case KeyEvent.VK_SPACE:{
                //子弹
                bflag = true;
                bulletShot();
                break;
            }

            case KeyEvent.VK_F1:{
                //导弹
                bflag = true;
                missileShot();
                break;
            }
            default:
                break;
        }
    }


    public void keyReleased(KeyEvent e) {
        flag = false;
        bflag = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = Direction.U;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.D;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.L;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.R;
                break;
            case KeyEvent.VK_SPACE:
                //子弹
                //if(this.bullets.size()<=5)
                bulletShot();
                break;
            case KeyEvent.VK_F1:
                //导弹
                missileShot();
                break;
            default:
                break;
        }
    }
}
