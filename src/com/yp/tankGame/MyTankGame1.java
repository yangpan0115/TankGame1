package com.yp.tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTankGame1 extends JFrame implements ActionListener{
    public static void main(String[] args) {
        new MyTankGame1();
    }
    MyPanel myPanel;
    public MyTankGame1(){
        this.setTitle("坦克大战1.0");
        this.setLocation(10,10);
        this.setSize(1350,660);//窗口大小

        this.setResizable(false);//窗口大小不变
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户单击窗口的关闭按钮时程序执行的操作--关闭窗口
        this.setAlwaysOnTop(true);//将框架锁定在最前面，不被覆盖

        //菜单栏
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        JMenuBar jmb = new JMenuBar();
        JMenu jm1 = new JMenu("游戏");
        JMenu jm2 = new JMenu("暂停/继续");
        JMenu jm3 = new JMenu("游戏级别");

        jm1.setFont(new Font("TankGame", Font.BOLD, 15));
        jm2.setFont(new Font("TankGame", Font.BOLD, 15));
        jm3.setFont(new Font("TankGame", Font.BOLD, 15));

        JMenuItem jmi1 = new JMenuItem("开始新的游戏");
        JMenuItem jmi2 = new JMenuItem("退出");

        JMenuItem jmi3 = new JMenuItem("暂停");
        JMenuItem jmi4 = new JMenuItem("继续");

        JMenuItem jmi5 = new JMenuItem("级别1");
        JMenuItem jmi6 = new JMenuItem("级别2");
        JMenuItem jmi7 = new JMenuItem("级别3");

        jmi1.setFont(new Font("TankGame", Font.BOLD, 15));
        jmi2.setFont(new Font("TankGame", Font.BOLD, 15));
        jmi3.setFont(new Font("TankGame", Font.BOLD, 15));
        jmi4.setFont(new Font("TankGame", Font.BOLD, 15));
        jmi5.setFont(new Font("TankGame", Font.BOLD, 15));
        jmi6.setFont(new Font("TankGame", Font.BOLD, 15));
        jmi7.setFont(new Font("TankGame", Font.BOLD, 15));

        jm1.add(jmi1);
        jm1.add(jmi2);
        jm2.add(jmi3);
        jm2.add(jmi4);
        jm3.add(jmi5);
        jm3.add(jmi6);
        jm3.add(jmi7);
        jmb.add(jm1);
        jmb.add(jm2);
        jmb.add(jm3);


        jmi1.addActionListener(this);
        jmi1.setActionCommand("Start");
        jmi2.addActionListener(this);
        jmi2.setActionCommand("Exit");
        jmi3.addActionListener(this);
        jmi3.setActionCommand("Stop");
        jmi4.addActionListener(this);
        jmi4.setActionCommand("Continue");
        jmi5.addActionListener(this);
        jmi5.setActionCommand("Level1");
        jmi6.addActionListener(this);
        jmi6.setActionCommand("Level2");
        jmi7.addActionListener(this);
        jmi7.setActionCommand("Level3");
        //MenuBar放到JFrame上
        this.setJMenuBar(jmb);

        myPanel = new MyPanel();
        this.add(myPanel);
        this.addKeyListener(myPanel);//注册监听
        //启动线程
        Thread t = new Thread(myPanel);
        t.start();
        this.setVisible(true);//是否可视化
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start")){
            this.dispose();
            new MyTankGame1().myPanel.state = 2;
        }else if(e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }else if(e.getActionCommand().equals("Stop")) {
            if(myPanel.state == 2)
                myPanel.state = 3;
        }else if(e.getActionCommand().equals("Continue")){
            if(myPanel.state == 3)
                myPanel.state = 2;
        }else if(e.getActionCommand().equals("Level1")){
            if(myPanel.state == 2){
                this.dispose();
                myPanel.ensize = 2;
                myPanel.bsize = 1;
                new MyTankGame1().myPanel.state = 2;
            }
        }else if(e.getActionCommand().equals("Level2")){
            if(myPanel.state == 2){
                this.dispose();
                myPanel.ensize = 3;
                myPanel.bsize = 2;
                new MyTankGame1().myPanel.state = 2;
            }
        }else if(e.getActionCommand().equals("Level3")){
            if(myPanel.state == 2){
                this.dispose();
                myPanel.ensize = 4;
                myPanel.bsize = 3;
                new MyTankGame1().myPanel.state = 2;
            }
        }
    }
}
