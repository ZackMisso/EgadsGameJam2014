package events;
import entities.Follower;
public abstract class Event{
    private double xpos;
    private double ypos;
    private double threshold;
    
    public Event(double x,double y){
        xpos=x;
        ypos=y;
        threshold=2.0; // edit as needed
    }
    
    public boolean shouldFire(Follower follower){
        return follower.distanceBetween(xpos,ypos)<threshold;
    }
    
    public String toString(){
        return "";
    }
    
    public abstract void fire(Follower follower);
    
    // getter methods
    public double getXpos(){return xpos;}
    public double getYpos(){return ypos;}
    
    // setter methods
    public void setXpos(double param){xpos=param;}
    public void setYpos(double param){ypos=param;}
    public void setThreshold(double param){threshold=param;}
}
