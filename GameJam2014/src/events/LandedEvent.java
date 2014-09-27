package events;
import entities.Follower;
public class LandedEvent extends Event{
    public LandedEvent(double x,double y){
        super(x,y);
    }
    
    public void fire(Follower follower){
        follower.setYpos(getYpos());
    }
    
    public String toString(){
        return "LANDED";
    }
}
