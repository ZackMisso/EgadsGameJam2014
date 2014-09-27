package entities.obstacles;
import entities.Block;
import gameState.DimensionState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
public class Bridge extends Block implements Dependent{
    private Image image;
    private boolean activated;
    
    public Bridge(double x,double y,int w,int h){
        super(Color.BLUE,x,y,w,h);
        activated=false;
        initializeImage();
        update();
    }
    
    private void initializeImage(){
        try{
            image=ImageIO.read(new File("resources/Bridge.png"));
        }
        catch(IOException e) {System.out.println("Could Not Read Images");}
    }
    
    //public void update(){
    //    
    //}
    
    public void dependentFire(){
        activated=true;
        gs.getBlocks().add(this);
    }
    
    public void drawRectForm(Graphics2D g){
        drawImageForm(g);
    }
    
    public void drawImageForm(Graphics2D g){
        if(activated)
            g.drawImage(image,(int)xpos,(int)ypos,null);
        //g.setColor(Color.WHITE);
        //g.drawRect((int)xpos,(int)ypos,width,height);
    }
    
    // getter methods
    public boolean getActivated(){return activated;}
    
    // setter methods
    public void setActivated(boolean param){activated=param;}
}