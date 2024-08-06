package meiro.view;

import java.util.ArrayList;
import meiro.bean.BlockBean;
import meiro.process.Maze;

public class ConsoleManger {
    public static void show(ArrayList<BlockBean> blockAry){
        for(int i = 0; i < blockAry.size(); i++){
            if(blockAry.get(i).isWall()){
                System.out.print("⬛️");
            }else{
                System.out.print("  ");
            }
            if((i+1) % Maze.MAZE_SIZE == 0){
                System.out.println();
            }
        }
    }    
}
