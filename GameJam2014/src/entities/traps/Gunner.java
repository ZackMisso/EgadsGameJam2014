package entities.traps;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
public class Gunner extends Trap{
    private Image notFiringImg;
    private Image firingImg;
    private int interval;
    private int cnt;
    private boolean firing;
    
    public Gunner(double x,double y,int w,int h){
        super(x,y,w,h);
    }
    
    public void initializeImages(){
        try{
            notFiringImg=ImageIO.read(new File("resources/gunnerActivated.png"));
            firingImg=ImageIO.read(new File("resources/gunnerNotActivated.png"));
            //current=notFiringImg;
        }
        catch(IOException e) {System.out.println("Could Not Read Images");}
    }

    public int getWidth(){
        if(firing)
            return 96;
        else
            return 12;
    }
    
    public void update(){
        cnt--;
        if(cnt==0){
            firing=!firing;
            cnt=interval;
        }
    }
    
    public void drawImageForm(Graphics2D g){
        if(firing)
            g.drawImage(firingImg,(int)xpos,(int)ypos,null);
        else
            g.drawImage(notFiringImg,(int)xpos,(int)ypos,null);
    }
}