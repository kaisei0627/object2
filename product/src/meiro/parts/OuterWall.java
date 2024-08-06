package meiro.parts;

import java.util.ArrayList;
import meiro.bean.BlockBean;
import meiro.process.Maze;

public class OuterWall {
    
    public void init(ArrayList<BlockBean> blockAry){
        blockAry.clear();
        for(int i = 0; i < Maze.MAZE_SIZE; i++){
            for(int j = 0; j < Maze.MAZE_SIZE; j++){
                BlockBean bb = new BlockBean();
                if(i == 0 || i == Maze.MAZE_SIZE - 1 || j == 0 || j == Maze.MAZE_SIZE -1){
                    bb.setWall(true);
                    bb.setFixed(true);
                }else{
                    bb.setWall(false);
                    bb.setFixed(false);
                }
                if((i == 0 && j == 1) || (i == Maze.MAZE_SIZE - 2 && j == Maze.MAZE_SIZE - 1)){
                    bb.setWall(false);
                    bb.setFixed(true);
                }
                blockAry.add(bb);
            }
        }
    }
}
