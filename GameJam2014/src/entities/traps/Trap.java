package entities.traps;
import entities.GameEntity;
import java.awt.Graphics2D;
public class Trap extends GameEntity{
    private boolean activated;
    
    public Trap(double x,double y,int w,int h){
        super(x,y,w,h);
        activated=false;
    }
    
    public void update(){
        // implement
    }
    
    public void drawImageForm(Graphics2D g){
        // implement
    }
    
    // getter methods
    public boolean getActivated(){return activated;}
    
    // setter methods
    public void setActivated(boolean param){activated=param;}
}
