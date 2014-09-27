package entities.obstacles;
import entities.GameEntity;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Lever extends GameEntity{
    private Image activated;
    private Image notActivated;
    private int dependID;
    private Dependent depend;
    private boolean act;
    
    public Lever(double x,double y,int w,int h){
        super(x,y,w,h);
        depend=null;
        act=false;
        try{
            initImages();
        }catch(IOException e){}
    }

    public void initImages() throws IOException{
        activated=ImageIO.read(new File("resources/leverActive.png"));
        notActivated=ImageIO.read(new File("resources/leverNotActive.png"));
    }
    
    public void update(){
        // does nothing
    }

    public void activate(){
        act=true;
        if(depend!=null)
            depend.dependentFire();
    }
    
    public void drawImageForm(Graphics2D g){
        if(act)
            g.drawImage(activated,(int)xpos,(int)ypos,null);
        else
            g.drawImage(notActivated,(int)xpos,(int)ypos,null);
    }
    
    public void setDependent(Dependent d){
        depend = d;
    }
    
    public void setdependID(int i){
        dependID=i;
    }
    
    // getter method
    public boolean getActivated(){return act;}
    public int getdependID(){return dependID;}
}