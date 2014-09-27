
package entities;

import animations.Animation;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Mark
 */
public class Portal extends GameEntity {
    /* INHEIRITED FIELDS:
    
    protected TileMap map;
    protected Color color;
    protected double xpos;
    protected double ypos;
    protected double cxpos;
    protected double cypos;
    protected double xtemp;
    protected double ytemp;
    protected double dx;
    protected double dy;
    protected int width;
    protected int height;
    protected int cwidth;
    protected int cheight;
    protected DimensionState gs;
    protected boolean falling;
    protected boolean onScreen;
    */
    
    final String imageloc = "resources/portal.png";
    Animation interior;
    private int DimensionStateID;
    private boolean fired;
    private int followerthreshold = 1;
    
    public Portal(double x, double y, int w, int h, int ID) throws IOException{
        super(x,y,w,h);
        ArrayList<Image> images = new ArrayList<Image>();
        BufferedImage img = ImageIO.read(new File("resources/portalinterior_animated_strip360.png"));
        for(int i=0; i<360; i++){
            images.add(img.getSubimage(i*25, 0, 24, 24));
        }
        interior = new Animation(0,1,images);
        DimensionStateID=ID;
        fired=false;
    }

    public boolean fired(){return fired;}
    public void fire(){fired=true;}
    
    public void drawImageForm(Graphics2D g){
        try{
            g.drawImage(ImageIO.read(new File("resources/portalexterior.png")), (int)xpos-4, (int)ypos-2, null);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        g.drawImage(interior.getCurrentImage(), (int)xpos, (int)ypos, null);
    }
    
    public void drawImageForm(Graphics2D g,double xOffset){
        try{
            g.drawImage(ImageIO.read(new File("resources/portalexterior.png")), (int)(xpos-4-xOffset), (int)ypos-2, null);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        g.drawImage(interior.getCurrentImage(), (int)(xpos-xOffset), (int)ypos, null);
    }
    public boolean testFollowers(int numFollowers){return numFollowers >= followerthreshold;}
    public int getThreshold(){return followerthreshold;}
    public int getDimensionID(){return DimensionStateID;}
    public void update() {
        interior.update();
    }
    public void consumeFollower(Follower victim){
        victim.delete();
        followerthreshold--;
    }
}
