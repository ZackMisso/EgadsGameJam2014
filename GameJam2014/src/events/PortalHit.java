package events;
import entities.Follower;
import entities.Portal;
public class PortalHit extends Event{
    private Portal portal;
    public PortalHit(Portal p,double x,double y){
        super(x,y);
        setThreshold(10.0);
        portal=p;
    }
    
    //public boolean shouldFire(Follower follower){return !follower.isFalling();}
    
    public void fire(Follower follower){
        //follower.setJump(true);
        portal.consumeFollower(follower);
    }
    
    public String toString(){
        return "JUMP";
    }
}
