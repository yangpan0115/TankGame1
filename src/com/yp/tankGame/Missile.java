package com.yp.tankGame;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

//导弹
public class Missile{
    public int x;
    public int y;
    public Direction direction;
    public int speed = 10;
    public boolean isLive = true;

    public static final int width = 10;
    public static final int height = 10;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] missileImage = null;
    private static Map<Integer, Image> missileimgs = new HashMap<Integer, Image>(); // 定义Map键值对，是不同方向对应不同的弹头

    static {
        missileImage = new Image[]{
                tk.getImage(Missile.class.getResource("/images/missile_U.png")),
                tk.getImage(Missile.class.getResource("/images/missile_D.png")),
                tk.getImage(Missile.class.getResource("/images/missile_L.png")),
                tk.getImage(Missile.class.getResource("/images/missile_R.png")),
        };
        // 加入Map容器
        missileimgs.put(0,missileImage[0]);
        missileimgs.put(1,missileImage[1]);
        missileimgs.put(2,missileImage[2]);
        missileimgs.put(3,missileImage[3]);
    }

    public Missile(int x,int y,Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Missile(){}

    public void move(){
        switch(direction){
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
        //导弹何时死亡
        if(x<0||x> MyPanel.PanelWidth- Bullet.width||y<0||y> MyPanel.PanelHeight- Bullet.height){
            this.isLive = false;
        }
    }


    /**
     * drawBullet (子弹坐标x,y,画笔g,方向，子弹类型) 方法介绍：
     */
    public void drawMissile(int x, int y, Graphics g, Direction direction) {
        switch (direction) {
            case U:
                // 向上
                g.drawImage(missileimgs.get(0), x, y,25,25, null);
                break;
            case D:
                // 向下w
                g.drawImage(missileimgs.get(1), x, y,25,25, null);
                break;
            case L:
                // 向左
                g.drawImage(missileimgs.get(2), x, y, 25,25,null);
                break;
            case R:
                // 向右
                g.drawImage(missileimgs.get(3), x, y, 25,25,null);
                break;
            default:
                break;
        }
        move();
    }

    //攻击
    //1 攻击坦克
    public void missileTank(Tank tank){
        if (this.getRect().intersects(tank.getRect())) {
            // 坦克死亡
            tank.isLive = false;
            // 击中，子弹死亡
            this.isLive = false;
        }
    }

    //2 攻击金属墙
    public void hitMetalWall(MetalWall mw){
        if (this.getRect().intersects(mw.getRect())) {
            mw.isLive = false;
            this.isLive = false;
        }
    }

    //3 攻击普通墙
    public void hitCommonWall(CommonWall cw){
        if(this.getRect().intersects(cw.getRect())){
            cw.isLive = false;
            this.isLive = false;
        }
    }
    //4 攻击Boss
    public void missileBoss(Boss boss){
        if (this.getRect().intersects(boss.getRect())) {
            if(boss.life >= 20) {
                // 坦克死亡
                boss.lifeDown();
                // 击中，子弹死亡
                this.isLive = false;
            }else{
                // 坦克死亡
                boss.isLive = false;
                // 击中，子弹死亡
                this.isLive = false;
            }
        }
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }
}
