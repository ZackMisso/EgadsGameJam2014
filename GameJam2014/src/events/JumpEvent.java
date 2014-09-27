package events;
import entities.Follower;
public class JumpEvent extends Event{
    public JumpEvent(double x,double y){
        super(x,y);
    }
    
    public boolean shouldFire(Follower follower){return !follower.isFalling();}
    
    public void fire(Follower follower){
        follower.setJump(true);
    }
    
    public String toString(){
        return "JUMP";
    }
}