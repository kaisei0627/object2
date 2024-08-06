package meiro.process;

import java.util.ArrayList;
import meiro.bean.BlockBean;
import meiro.parts.OuterWall;
import meiro.parts.Path;
import meiro.view.ConsoleManger;

public class Maze {
    //迷路の大きさ
    public final static int MAZE_SIZE = 20;
    //迷路始まり（左上）
    public final static int START_INDEX = MAZE_SIZE + 1;
    //迷路終わり（右下）
    public final static int END_INDEX = MAZE_SIZE * (MAZE_SIZE - 1) - 2;
    //ブロックのリスト
    private static ArrayList<BlockBean> blockAry = new ArrayList<BlockBean>();
    public static void main(String[] args){
        //外枠
        OuterWall w = new OuterWall();
        w.init(blockAry);
        //迷路作成
        Path path = new Path();
        path.createPath(blockAry);
        //迷路表示
        ConsoleManger.show(blockAry);
    }
}
