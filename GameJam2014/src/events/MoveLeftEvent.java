package events;
import entities.Follower;
public class MoveLeftEvent extends Event{
    public MoveLeftEvent(double x,double y){
        super(x,y);
    }
    
    public boolean shouldFire(Follower follower){return follower.getXpos()>follower.getHolder().getXpos()+30;}
    
    public void fire(Follower follower){
        follower.setLeft(true);
    }
    
    public String toString(){
        return "LEFT";
    }
}