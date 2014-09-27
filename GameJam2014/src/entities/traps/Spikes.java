package entities.traps;
import animations.Animation;
import entities.GameEntity;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
public class Spikes extends GameEntity{
    Animation animation;
    
    public Spikes(double x,double y,int w,int h){
        super(x,y,w,h);
        initImage();
    }
    
    private void initImage(){
        ArrayList<Image> images=new ArrayList<>();
        try{
            images.add(ImageIO.read(new File("resources/spikes1.png")));
            images.add(ImageIO.read(new File("resources/spikes2.png")));
        }
        catch(IOException e) {System.out.println("Could Not Read Images");}
        animation=new Animation(0,20,images);
    }
    
    public void update(){
        animation.update();
    }
    
    public void drawImageForm(Graphics2D g){
        g.drawImage(animation.getCurrentImage(),(int)xpos,(int)ypos,null);
    }
}