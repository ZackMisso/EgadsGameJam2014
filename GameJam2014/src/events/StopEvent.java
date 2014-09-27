package events;
import entities.Follower;
public class StopEvent extends Event{
    public StopEvent(double x,double y){
        super(x,y);
    }
    
    public boolean shouldFire(Follower follower){
        return true;
    }
    
    public void fire(Follower follower){
        //System.out.println("DIRES");
        follower.setDx(0);
        follower.setLeft(false);
        follower.setRight(false);
    }
    
    public String toString(){
        return "STOP";
    }
}