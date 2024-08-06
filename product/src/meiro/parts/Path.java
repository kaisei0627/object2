package meiro.parts;

import java.util.ArrayList;
import java.util.Random;
import meiro.bean.BlockBean;
import meiro.checker.RootChecker;
import meiro.process.Maze;
import meiro.view.ConsoleManger;


public class Path {

    public void createPath(ArrayList<BlockBean> blockAry){
    // 正解の道を生成する
    int cnt = 0;
    while (!tryCreatePath(blockAry, Maze.START_INDEX, Maze.END_INDEX)) {
        OuterWall w = new OuterWall();
        w.init(blockAry);
        cnt++;
    }
    System.out.println("DEBUG: Retry " + cnt + "\n");

    // 最終的な確認
    ArrayList<BlockBean> arry = new ArrayList<>(blockAry);
    for (int i = 0; i < arry.size(); i++) {
        if (!arry.get(i).isFixed()) {
            BlockBean bb = new BlockBean();
            bb.setWall(true);
            arry.set(i, bb);
        }
    }
    ConsoleManger.show(arry);
    System.out.println();

    // 分岐の道を生成する
    for (int i = Maze.START_INDEX; i < Maze.END_INDEX; i++) {
        tryCreatePath(blockAry, i, Maze.END_INDEX);
    }

    for (int i = 0; i < blockAry.size(); i++) {
        if (!blockAry.get(i).isFixed()) {
            BlockBean bb = new BlockBean();
            bb.setWall(true);
            blockAry.set(i, bb);
        }
    }

    // 迷路の微調整
    RootChecker r = new RootChecker();
    r.fixDetail(blockAry, Maze.START_INDEX, Maze.END_INDEX);
}
    private boolean tryCreatePath(ArrayList<BlockBean> blockAry, int startIndex, int endIndex){
    boolean[] flg = new boolean[4];
    RootChecker r = new RootChecker();
    int currentIndex = startIndex;

    while (currentIndex != endIndex) {
        // 道を伸ばせるかどうか設定
        for (int i = 0; i < flg.length; i++) {
            flg[i] = false;
        }

        // 上
        if (currentIndex - Maze.MAZE_SIZE >= 0 && !blockAry.get(currentIndex - Maze.MAZE_SIZE).isWall() && !blockAry.get(currentIndex - Maze.MAZE_SIZE).isFixed()) {
            flg[0] = r.canPath(currentIndex - Maze.MAZE_SIZE, blockAry);
        }
        // 下
        if (currentIndex + Maze.MAZE_SIZE < blockAry.size() && !blockAry.get(currentIndex + Maze.MAZE_SIZE).isWall() && !blockAry.get(currentIndex + Maze.MAZE_SIZE).isFixed()) {
            flg[1] = r.canPath(currentIndex + Maze.MAZE_SIZE, blockAry);
        }
        // 左
        if (currentIndex % Maze.MAZE_SIZE > 0 && !blockAry.get(currentIndex - 1).isWall() && !blockAry.get(currentIndex - 1).isFixed()) {
            flg[2] = r.canPath(currentIndex - 1, blockAry);
        }
        // 右
        if ((currentIndex + 1) % Maze.MAZE_SIZE != 0 && !blockAry.get(currentIndex + 1).isWall() && !blockAry.get(currentIndex + 1).isFixed()) {
            flg[3] = r.canPath(currentIndex + 1, blockAry);
        }

        // 道を伸ばせる方向を選択
        boolean canMove = false;
        for (int i = 0; i < flg.length; i++) {
            if (flg[i]) {
                canMove = true;
                break;
            }
        }

        if (!canMove) {
            return false;
        } else {
            BlockBean bb = new BlockBean();
            bb.setWall(false);
            bb.setFixed(true);
            bb.setPath(true);
            blockAry.set(currentIndex, bb);

            Random rnd = new Random();
            int randomDirection = rnd.nextInt(flg.length);
            while (!flg[randomDirection]) {
                randomDirection = rnd.nextInt(flg.length);
            }

            // 現在のインデックスを更新
            switch (randomDirection) {
                case 0: currentIndex -= Maze.MAZE_SIZE; break; // 上
                case 1: currentIndex += Maze.MAZE_SIZE; break; // 下
                case 2: currentIndex -= 1; break; // 左
                case 3: currentIndex += 1; break; // 右
            }
        }
    }

    // 終了点のブロックを設定
    BlockBean bb = new BlockBean();
    bb.setWall(false);
    bb.setFixed(true);
    bb.setPath(true);
    blockAry.set(endIndex, bb);

    return true;
}


}
