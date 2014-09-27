package animations;
import java.awt.Graphics2D;
public class Star {
    private int xpos;
    private int ypos;
    private int timer;
    
    public Star(int x,int y,int t){
        xpos=x;
        ypos=y;
        timer=t;
    }
    
    public boolean update(){
        return --timer==0;
    }
    
    public void draw(Graphics2D g){
        g.drawRect(xpos,ypos,8,8);
    }
}
