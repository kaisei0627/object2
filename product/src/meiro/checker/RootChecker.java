package meiro.checker;

import java.util.ArrayList;
import meiro.bean.BlockBean;
import meiro.process.Maze;


public class RootChecker {
    public boolean canPath(int index, ArrayList<BlockBean> blockAry){
        boolean flg[] = new boolean[4];//上下左右（１ブロックに対する、道を伸ばす）
        //道を伸ばせるかどうかのフラグ配列初期設定
        for(int i = 0; i < flg.length; i++){
            flg[i] = false;
        }
        //上
        if(!blockAry.get(index - Maze.MAZE_SIZE).isPath()){
            flg[0] = true;
        }
        //下
        if(!blockAry.get(index + Maze.MAZE_SIZE).isPath()){
            flg[1] = true;
        }
        //右
        if(!blockAry.get(index - 1).isPath()){
            flg[2] = true;
        }
        //左
        if(!blockAry.get(index + 1).isPath()){
            flg[3] = true;
        }

        //どこにも道を伸ばせない場合はfalse
        int cnt = 0;
        for(int i = 0; i < flg.length; i++){
            if(flg[i]){
                cnt++;
            }
        }
        if(cnt == flg.length){
            return true;
        }else{
            return false;
        }
    }
    public void fixDetail(ArrayList<BlockBean> blockAry, int startIndex, int endIndex) {
    for (int i = startIndex; i < endIndex; i++) {
        // 角のブロックを修正
        if ((i - Maze.MAZE_SIZE >= 0 && blockAry.get(i - Maze.MAZE_SIZE).isWall() && blockAry.get(i - 1).isWall() && !blockAry.get(i + Maze.MAZE_SIZE).isWall()
             && !blockAry.get(i + 1).isWall() && !blockAry.get(i + Maze.MAZE_SIZE + 1).isWall())
         || (i - Maze.MAZE_SIZE >= 0 && blockAry.get(i - Maze.MAZE_SIZE).isWall() && blockAry.get(i + 1).isWall() && !blockAry.get(i + Maze.MAZE_SIZE).isWall() 
             && !blockAry.get(i - 1).isWall() && !blockAry.get(i + Maze.MAZE_SIZE - 1).isWall())
         || (i + Maze.MAZE_SIZE < blockAry.size() && blockAry.get(i + Maze.MAZE_SIZE).isWall() && blockAry.get(i - 1).isWall() && !blockAry.get(i - Maze.MAZE_SIZE).isWall()
             && !blockAry.get(i + 1).isWall() && !blockAry.get(i - Maze.MAZE_SIZE + 1).isWall())
         || (i + Maze.MAZE_SIZE < blockAry.size() && blockAry.get(i + Maze.MAZE_SIZE).isWall() && blockAry.get(i + 1).isWall() && !blockAry.get(i - Maze.MAZE_SIZE).isWall()
             && !blockAry.get(i - 1).isWall() && !blockAry.get(i - Maze.MAZE_SIZE - 1).isWall())) {
            BlockBean bb = new BlockBean();
            bb.setWall(true);
            bb.setFixed(true);
            bb.setPath(true);
            blockAry.set(i, bb);
        }
    }
    //単体ブロックを繋げる処理は別途実装する必要があります
}

}
