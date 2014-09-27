package events;
import entities.Follower;
public class MoveRightEvent extends Event{
    public MoveRightEvent(double x,double y){
        super(x,y);
    }
    
    public boolean shouldFire(Follower follower){return follower.getXpos()<follower.getHolder().getXpos()-30;}
    
    public void fire(Follower follower){
        follower.setRight(true);
    }
    
    public String toString(){
        return "RIGHT";
    }
}