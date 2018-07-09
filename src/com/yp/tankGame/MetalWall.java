package com.yp.tankGame;

import java.awt.*;

public class MetalWall extends Wall {

    public MetalWall(int x,int y){
        super(x,y);
    }
    public MetalWall(){}

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] metalWallImage = null;

    //存储图片
    static{
        metalWallImage = new Image[]{
                tk.getImage(MetalWall.class.getResource("/images/metalWall.gif")),
        };
    }

    //画出墙
    public void drawMetalWall(Graphics g){
        g.drawImage(metalWallImage[0],x,y,40,40,null);
    }
}
