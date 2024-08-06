package meiro.bean;

public class BlockBean {
    boolean wall;
    boolean path;
    boolean fixed;

    public boolean isWall(){
        return wall;
    }
    public void setWall(boolean wall){
        this.wall = wall;
    }
    public boolean isPath(){
        return path;
    }
    public void setPath(boolean path){
        this.path = path;
    }
    public boolean isFixed(){
        return fixed;
    }
    public void setFixed(boolean fixed){
        this.fixed = fixed;
    }
}
