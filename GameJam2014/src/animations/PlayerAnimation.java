package animations;
import java.awt.Image;
public class PlayerAnimation{
    private Animation standing;
    private Animation running;
    private Animation jumping;
    private Animation current;
    private boolean st;
    private boolean run;
    private boolean jmp;
    
    public PlayerAnimation(){
        initializeRunning();
        initializeJumping();
        initializeStanding();
        st=true;
        current=standing;
    }
    
    private void initializeStanding(){
        // implement
    }
    
    private void initializeRunning(){
        // implement
    }
    
    private void initializeJumping(){
        // implement
    }
    
    public void update(){
        current.update();
    }
    
    public Image getCurrentImage(){
        return current.getCurrentImage();
    }
    
    public void startJump(){
        reset();
        jmp=true;
        jumping.reset();
        current=jumping;
    }
    
    public void startRun(){
        reset();
        run=true;
        running.reset();
        current=running;
    }
    
    public void startStand(){
        reset();
        st=true;
        standing.reset();
        current=standing;
    }
    
    private void reset(){
        st=false;
        run=false;
        jmp=false;
    }
    
    // getter methods
    public boolean getStanding(){return st;}
    public boolean getRinning(){return run;}
    public boolean getJumpint(){return jmp;}
}