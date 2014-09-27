package animations;
import java.awt.Graphics2D;
import java.awt.Color;
public class MenuTitle {
    // Dimension
    private int xpos;
    private int ypos;
    private int xOffset;
    private int yOffset;
    
    public MenuTitle(int x,int y){
        xpos=x;
        ypos=y;
        xOffset=25;
        yOffset=100;
    }
    
    public void draw(Graphics2D g){
        drawD(g);
        drawi(g);
        drawm(g);
        drawe(g);
        drawn(g);
        draws(g);
        drawi2(g);
        drawo(g);
        drawn2(g);
    }
    
    public void drawD(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.drawRect(xpos+xOffset,ypos-20+yOffset,25,120);
        g.drawRect(xpos+xOffset,ypos-20+yOffset,90,120);
    }
    public void drawi(Graphics2D g){
        g.drawRect(xpos+120+xOffset,ypos+20+yOffset,20,20);
        g.drawRect(xpos+120+xOffset,ypos+50+yOffset,20,50);
    }
    public void drawm(Graphics2D g){
        g.drawRect(xpos+160+xOffset,ypos+40+yOffset,20,60);
        g.drawRect(xpos+190+xOffset,ypos+40+yOffset,20,60);
        g.drawRect(xpos+220+xOffset,ypos+40+yOffset,20,60);
        g.drawRect(xpos+170+xOffset,ypos+20+yOffset,30,40);
        g.drawRect(xpos+200+xOffset,ypos+20+yOffset,30,40);
    }
    public void drawe(Graphics2D g){
        g.drawRect(xpos+260+xOffset,ypos+20+yOffset,20,80);
        g.drawRect(xpos+260+xOffset,ypos+20+yOffset,50,20);
        g.drawRect(xpos+260+xOffset,ypos+50+yOffset,50,20);
        g.drawRect(xpos+260+xOffset,ypos+80+yOffset,50,20);
    }
    public void drawn(Graphics2D g){
        g.drawRect(xpos+330+xOffset,ypos+40+yOffset,20,60);
        g.drawRect(xpos+360+xOffset,ypos+40+yOffset,20,60);
        g.drawRect(xpos+330+xOffset,ypos+20+yOffset,40,20);
    }
    public void draws(Graphics2D g){
        g.drawRect(xpos+410+xOffset,ypos+80+yOffset,40,20);
        g.drawRect(xpos+410+xOffset,ypos+50+yOffset,40,20);
        g.drawRect(xpos+410+xOffset,ypos+20+yOffset,40,20);
        g.drawRect(xpos+440+xOffset,ypos+60+yOffset,20,30);
        g.drawRect(xpos+400+xOffset,ypos+30+yOffset,20,30);
    }
    public void drawi2(Graphics2D g){
        g.drawRect(xpos+480+xOffset,ypos+20+yOffset,20,20);
        g.drawRect(xpos+480+xOffset,ypos+50+yOffset,20,50);
    }
    public void drawo(Graphics2D g){
        g.drawRect(xpos+520+xOffset,ypos+40+yOffset,20,50);
        g.drawRect(xpos+560+xOffset,ypos+40+yOffset,20,50);
        g.drawRect(xpos+530+xOffset,ypos+30+yOffset,40,20);
        g.drawRect(xpos+530+xOffset,ypos+80+yOffset,40,20);
    }
    public void drawn2(Graphics2D g){
        g.drawRect(xpos+600+xOffset,ypos+40+yOffset,20,60);
        g.drawRect(xpos+630+xOffset,ypos+40+yOffset,20,60);
        g.drawRect(xpos+600+xOffset,ypos+20+yOffset,40,20);
    }
}