package com.yp.tankGame;

import java.awt.*;

public class Tree {
    int x;
    int y;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] treeImags = null;

    static {
        treeImags = new Image[]{
                tk.getImage(CommonWall.class.getResource("/Images/tree.gif")),
        };
    }

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawTree(Graphics g) {           //画出树
        g.drawImage(treeImags[0], x, y, null);
    }
}
