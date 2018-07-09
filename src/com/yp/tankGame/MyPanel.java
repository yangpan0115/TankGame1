package com.yp.tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {

    public static int PanelWidth = 1100;
    public static int PanelHeight = 610;

    public static int START = 1;
    public int state = START;

    ImageIcon icon;
    Image img;
    //初始化我方坦克
    private int mysize = 1;
    Vector<MyTank> myTanks = new Vector<MyTank>();
    //定义敌军数量
    public static int ensize = 2;
    //定义敌军
    Vector<EnemyTank> ets = new Vector<EnemyTank>();
    //定义Boss
    public static int bsize = 1;
    Vector<Boss> bosses = new Vector<Boss>();
    //定义炸弹集合
    Vector<Bomb> bombs = new Vector<Bomb>();
    //定义普通墙
    int cwsize = 1;
    Vector<CommonWall> cws = new Vector<CommonWall>();
    //定义金属墙
    int mwsize = 3;
    Vector<MetalWall> mws = new Vector<MetalWall>();
    //定义家
    Home home = null;
    //定义血包
    Blood blood = new Blood();
    //定义地雷
    int lmsize = 2;
    Vector<LandMine> landMines = new Vector<LandMine>();

    //定义树
    int treeSize = 4;
    Vector<Tree> trees = new Vector<Tree>();

    public MyPanel(){
        Bomb bomb = new Bomb(-100, -100);
        bombs.add(bomb);
        //设定我方坦克出现的位置
        for(int i=0;i<mysize;i++){
            MyTank myTank = new MyTank(500,400, Direction.U);
            myTanks.add(myTank);
        }
        //初始化敌方坦克
        for(int i=0;i<ensize;i++){
            EnemyTank et = new EnemyTank(i*150,50, Direction.D);
            ets.add(et);
        }
        //初始化Boss
        for(int i=0;i<bsize;i++){
            Boss boss = new Boss(1000-i*150,50,Direction.D);
            bosses.add(boss);
        }

        //初始化墙1
        for(int i=0;i<cwsize;i++){
            CommonWall home1 = new CommonWall(500+i*40,485);
            cws.add(home1);
        }

        for(int i=0;i<mwsize;i++){
            MetalWall mw1 = new MetalWall(200+i*40,300);
            mws.add(mw1);

            MetalWall mw2 = new MetalWall(500+i*40,300);
            mws.add(mw2);

            MetalWall mw3 = new MetalWall(800+i*40,300);
            mws.add(mw3);

            MetalWall home2 = new MetalWall(460,485+i*40);
            mws.add(home2);

            MetalWall home4 = new MetalWall(540,485+i*40);
            mws.add(home4);

        }
        //初始化家
        home = new Home(500,540);

        //初始化地雷
        for(int i=0;i<lmsize;i++){
            LandMine landMine = new LandMine(400+i*300,300);
            landMines.add(landMine);
        }

        //初始化tree
        for(int i=0;i<treeSize;i++){
            Tree tree1 = new Tree(i*30,200);
            trees.add(tree1);
            Tree tree2 = new Tree(i*30,240);
            trees.add(tree2);
            Tree tree3 = new Tree(i*30,460);
            trees.add(tree3);
            Tree tree4 = new Tree(i*30,500);
            trees.add(tree4);
            Tree tree5 = new Tree(300+i*30,460);
            trees.add(tree5);
            Tree tree6 = new Tree(300+i*30,500);
            trees.add(tree6);

            Tree tree7 = new Tree(800-i*30,460);
            trees.add(tree7);
            Tree tree8 = new Tree(800-i*30,500);
            trees.add(tree8);
            Tree tree9 = new Tree(1060-i*30,460);
            trees.add(tree9);
            Tree tree0 = new Tree(1060-i*30,500);
            trees.add(tree0);

            Tree tree01 = new Tree(1060-i*30,200);
            trees.add(tree01);
            Tree tree02 = new Tree(1060-i*30,240);
            trees.add(tree02);
        }


        icon=new ImageIcon(getClass().getResource("/images/bg1.png"));
        img=icon.getImage();
    }

    public void paintComponent(Graphics g) {
        switch (state){
            case 1:
                g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);

                g.setColor(Color.yellow);
                g.setFont(new Font("TankGame", Font.BOLD, 30));
                g.drawString("点击->游戏->开始新的游戏", 500, 350);
                break;
            default:
                super.paintComponent(g);
                //背景填充
                g.drawImage(img,0,0,PanelWidth,this.getHeight(),this);

                //实时情况
                g.setColor(Color.blue);
                g.setFont(new Font("TankGame", Font.BOLD, 20));
                g.drawString("实时情况:", 1110, 40);
                g.drawString("敌方坦克数量: ", 1110, 70);
                g.setFont(new Font("Tank", Font.ITALIC, 30));
                g.drawString("" + (ets.size()+bosses.size()), 1250, 70);
                g.setFont(new Font("Tank", Font.BOLD, 20));
                g.drawString("我方坦克生命值: ", 1110, 100);
                g.setFont(new Font("Tank", Font.ITALIC, 30));
                for(int i=0;i<myTanks.size();i++){
                    MyTank myTank = myTanks.get(i);
                    g.drawString("" + myTank.life, 1265, 100);
                }
                //帮助文档
                g.setFont(new Font("TankGame", Font.BOLD, 20));
                g.drawString("帮助文档:", 1110, 200);
                g.drawString("用→ ← ↑ ↓控制方向!", 1110, 230);
                g.drawString("空格键发射子弹!", 1110, 260);
                g.drawString("F1键发射导弹!", 1110, 290);
                g.drawString("攻击普通坦克", 1110, 320);
                g.drawString("用子弹即可!", 1110, 350);
                g.drawString("攻击Boss坦克", 1110, 380);
                g.drawString("必须用导弹!", 1110, 410);

                //画出墙
                //1 普通墙
                for(int i=0;i<cws.size();i++){
                    CommonWall cw = cws.get(i);
                    if(cw.isLive){
                        cw.drawCommonWall(g);
                    }else{
                        cws.remove(cw);
                        Bomb bomb = new Bomb(cw.getX(), cw.getY());
                        bombs.add(bomb);
                    }

                }
                //2 金属墙
                for(int i=0;i<mws.size();i++){
                    MetalWall mw = mws.get(i);
                    if(mw.isLive){
                        mw.drawMetalWall(g);
                    }else{
                        mws.remove(mw);
                        Bomb bomb = new Bomb(mw.getX(), mw.getY());
                        bombs.add(bomb);
                    }
                }

                //画出家
                if(home.isLive){
                    home.drawHome(g);
                }else{
                    for(int i=0;i<myTanks.size();i++) {
                        MyTank myTank = myTanks.get(i);
                        myTank.isLive = false;
                    }
                }

                //画出血包
                blood.drawBlood(g);

                //画出地雷
                for(int i=0;i<landMines.size();i++){
                    LandMine landMine = landMines.get(i);
                    if(landMine.isLive){
                        landMine.drawLandMine(g);
                    }else{
                        landMines.remove(landMine);
                    }
                }


                //画出炸弹
                for (int i = 0; i < bombs.size(); i++) {
                    // 取出炸弹
                    Bomb b = bombs.get(i);
                    b.drawBomb(g);
                    // 如果life为 0 就把炸弹从bombs去掉
                    if (b.life == 0) {
                        bombs.remove(b);
                    }
                }

                //画出我方坦克
                for(int i=0;i<myTanks.size();i++){
                    MyTank myTank = myTanks.get(i);
                    if(myTank.isLive){
                        myTank.drawLife(g);
                        myTank.drawHomeLife(g);
                        myTank.drawMytank(myTank.getX(),myTank.getY(),g,myTank.direction);

                        //吃血包
                        myTank.getBlood(blood);

                        //碰撞检测
                        //1 普通墙
                        for (int l = 0; l < cws.size(); l++) {
                            CommonWall cw = cws.get(l);
                            myTank.collideCommonWall(cw);
                        }
                        //2 金属墙
                        for (int j = 0; j < mws.size(); j++) {
                            MetalWall mw = mws.get(j);
                            myTank.collideMetalWall(mw);
                        }
                        //3 敌方坦克
                        for (int k = 0; k < ets.size(); k++) {
                            EnemyTank et = ets.get(k);
                            myTank.collideTank(et);
                        }
                        //4 Boss坦克
                        for(int b = 0;b < bosses.size();b++){
                            Boss boss = bosses.get(b);
                            myTank.collideTank(boss);
                        }
                        //5 家
                        myTank.collideHome(home);
                        //6 地雷
                        for(int m=0;m<landMines.size();m++){
                            LandMine landMine = landMines.get(m);
                            myTank.collideLandMine(landMine);
                        }


                        //从bullets中取出每一颗子弹，并画出
                        for(int j=0;j<myTank.bullets.size();j++){
                            Bullet myBullet = myTank.bullets.get(j);
                            //画出子弹
                            if(myBullet.isLive==true){
                                myBullet.drawBullet(myBullet.x,myBullet.y,g,myBullet.direction);

                                //我方子弹攻击
                                //取出敌方坦克，与他判断
                                for (int m=0; m < ets.size(); m++) {
                                    EnemyTank et = ets.get(m);
                                    if (et.isLive) {
                                        myBullet.bulletTank(et);
                                    }
                                }
                                //取出墙，与之判断
                                //1 普通墙
                                for(int k = 0;k < cws.size();k++){
                                    CommonWall cw = cws.get(k);
                                    if(cw.isLive){
                                        myBullet.hitCommonWall(cw);
                                    }
                                }
                                //2 金属墙
                                for(int l = 0;l < mws.size();l++){
                                    MetalWall mw = mws.get(l);
                                    if(mw.isLive){
                                        myBullet.hitMetalWall(mw);
                                    }
                                }
                            }else{
                                myTank.bullets.remove(myBullet);
                            }
                        }
                        //从mm中取出每颗导弹，并画出
                        for(int j=0;j<myTank.missiles.size();j++){
                            Missile missile = myTank.missiles.get(j);
                            //画出导弹
                            if(missile.isLive == true){
                                missile.drawMissile(missile.x,missile.y,g,missile.direction);
                                //我方导弹攻击
                                // 取出每个坦克，与他判断
                                for (int m = 0; m < ets.size(); m++) {
                                    EnemyTank et = ets.get(m);
                                    if (et.isLive) {
                                        missile.missileTank(et);
                                    }
                                }
                                //Boss坦克
                                for(int b = 0;b<bosses.size();b++){
                                    Boss boss = bosses.get(b);
                                    if(boss.isLive){
                                        missile.missileBoss(boss);
                                    }
                                }
                                //取出墙，与之判断
                                //1 普通墙
                                for (int k = 0; k < cws.size(); k++) {
                                    CommonWall cw = cws.get(k);
                                    if (cw.isLive) {
                                        missile.hitCommonWall(cw);
                                    }
                                }
                                //2 金属墙
                                for (int l = 0; l < mws.size(); l++) {
                                    MetalWall mw = mws.get(l);
                                    if (mw.isLive) {
                                        missile.hitMetalWall(mw);
                                    }
                                }
                            }else{
                                myTank.missiles.remove(missile);
                            }
                        }
                    }else{
                        myTanks.remove(myTank);
                        Bomb bomb = new Bomb(myTank.getX(),myTank.getY());
                        bombs.add(bomb);
                    }
                }

                //画出Boss
                for(int i=0;i<bosses.size();i++){
                    Boss boss = bosses.get(i);
                    if(boss.isLive){
                        boss.drawLife(g);
                        boss.drawBoss(boss.getX(),boss.getY(),g,boss.direction);
                        //吃血包
                        boss.getBlood(blood);
                        //碰撞检测
                        //1 普通墙
                        for(int l=0;l<cws.size();l++){
                            CommonWall cw = cws.get(l);
                            boss.collideCommonWall(cw);
                        }
                        //2 金属墙
                        for(int j=0;j<mws.size();j++){
                            MetalWall mw = mws.get(j);
                            boss.collideMetalWall(mw);
                        }
                        //3Boss坦克
                        for(int k=0;k<bosses.size();k++){
                            Boss boss1 = bosses.get(k);
                            boss.collideTank(boss1);
                        }
                        //4 家
                        boss.collideHome(home);
                        //5 地雷
                        for(int m=0;m<landMines.size();m++){
                            LandMine landMine = landMines.get(m);
                            boss.collideLandMine(landMine);
                        }
                        //添加子弹
                        for (int j = 0; j < boss.bullets.size(); j++) {
                            Bullet bossBullet = boss.bullets.get(j);
                            if (bossBullet.isLive) {
                                bossBullet.drawBullet(bossBullet.x, bossBullet.y,g,bossBullet.direction);

                                //攻击我方坦克
                                for(int k=0;k<myTanks.size();k++){
                                    MyTank myTank = myTanks.get(k);
                                    bossBullet.bulletTank(myTank);
                                    //碰撞检测
                                    //1 子弹
                                    for(int n=0;n<myTank.bullets.size();n++){
                                        Bullet myBullet = myTank.bullets.get(n);
                                        bossBullet.collideBullet(myBullet);
                                    }
                                    //2 导弹
                                    for(int nn = 0;nn<myTank.missiles.size();nn++){
                                        Missile missile = myTank.missiles.get(nn);
                                        bossBullet.collideMissile(missile);
                                    }
                                }
                                //攻击普通墙
                                for(int m=0;m<cws.size();m++){
                                    CommonWall cw = cws.get(m);
                                    bossBullet.hitCommonWall(cw);
                                }
                                //攻击金属墙
                                for(int l=0;l<mws.size();l++){
                                    MetalWall mw = mws.get(l);
                                    bossBullet.hitMetalWall(mw);
                                }
                                //攻击家
                                bossBullet.hitHome(home);

                            } else {
                                //子弹死亡
                                boss.bullets.remove(bossBullet);
                            }
                        }
                    }else{
                        bosses.remove(boss);
                        Bomb bomb = new Bomb(boss.getX(),boss.getY());
                        bombs.add(bomb);
                    }
                }
                // 画出敌方坦克
                for (int i = 0; i < ets.size(); i++) {
                    EnemyTank et = ets.get(i);
                    if (et.isLive) {
                        et.drawLife(g);
                        et.drawEnemytank(et.getX(), et.getY(), g, et.direction);
                        //吃血包
                        et.getBlood(blood);
                        //碰撞检测
                        //1 普通墙
                        for(int l=0;l<cws.size();l++){
                            CommonWall cw = cws.get(l);
                            et.collideCommonWall(cw);
                        }
                        //2 金属墙
                        for(int j=0;j<mws.size();j++){
                            MetalWall mw = mws.get(j);
                            et.collideMetalWall(mw);
                        }
                        //3 Boss坦克
                        for(int b=0;b<bosses.size();b++){
                            Boss boss = bosses.get(b);
                            et.collideTank(boss);
                        }
                        //4 其他坦克
                        for(int k=0;k<ets.size();k++){
                            EnemyTank et1 = ets.get(k);
                            et.collideTank(et1);
                        }
                        //5 家
                        et.collideHome(home);
                        //6 地雷
                        for(int m=0;m<landMines.size();m++){
                            LandMine landMine = landMines.get(m);
                            et.collideLandMine(landMine);
                        }

                        // 画出敌人坦克子弹
                        for (int j = 0; j < et.bullets.size(); j++) {
                            Bullet enemyBullet = et.bullets.get(j);
                            if (enemyBullet.isLive) {
                                enemyBullet.drawBullet(enemyBullet.x, enemyBullet.y,g,enemyBullet.direction);

                                //攻击我方坦克
                                for(int k=0;k<myTanks.size();k++){
                                    MyTank myTank = myTanks.get(k);
                                    enemyBullet.bulletTank(myTank);
                                    //碰撞检测
                                    //1 子弹
                                    for(int n=0;n<myTank.bullets.size();n++){
                                        Bullet myBullet = myTank.bullets.get(n);
                                        enemyBullet.collideBullet(myBullet);
                                    }
                                    //2 导弹
                                    for(int nn = 0;nn<myTank.missiles.size();nn++){
                                        Missile missile = myTank.missiles.get(nn);
                                        enemyBullet.collideMissile(missile);
                                    }
                                }
                                //攻击普通墙
                                for(int m=0;m<cws.size();m++){
                                    CommonWall cw = cws.get(m);
                                    enemyBullet.hitCommonWall(cw);
                                }
                                //攻击金属墙
                                for(int l=0;l<mws.size();l++){
                                    MetalWall mw = mws.get(l);
                                    enemyBullet.hitMetalWall(mw);
                                }
                                //攻击家
                                enemyBullet.hitHome(home);

                            } else {
                                //子弹死亡
                                et.bullets.remove(enemyBullet);
                            }
                        }
                    }else{
                        //坦克死亡
                        ets.remove(et);
                        Bomb bomb = new Bomb(et.getX(),et.getY());
                        bombs.add(bomb);
                    }
                }

                //画出tree
                for(int i=0;i<trees.size();i++){
                    Tree tree = trees.get(i);
                    tree.drawTree(g);
                }
                //胜负
                g.setColor(Color.red);
                //赢
                if(ets.size()==0&&bosses.size()==0&&home.isLive&&myTanks.size()!=0){
                    g.setFont(new Font("TankGame",Font.BOLD,60));
                    g.drawString("你赢了!",450,100);
                }
                //输
                if((ets.size()!=0||bosses.size()!=0)&&(home.isLive == false||myTanks.size() == 0)){
                    g.setFont(new Font("TankGame",Font.BOLD,60));
                    g.drawString("你输了!",450,100);

                }

                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 实现接口 根据按键上下左右移动 可以控制速度和移动
    @Override
    public void keyPressed(KeyEvent e) {
        for(int m=0;m<myTanks.size();m++) {
            MyTank myTank = myTanks.get(m);
            myTank.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(int m=0;m<myTanks.size();m++) {
            MyTank myTank = myTanks.get(m);
            myTank.keyReleased(e);
        }
    }

    @Override
    public void run() {
        while(true){
            switch (state){
                case 3:
                    break;
                default:
                    repaint();
                    break;
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
