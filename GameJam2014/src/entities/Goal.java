
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
public class Goal extends GameEntity {
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
    private int DimensionStateID;
    private boolean fired;
    private int followerthreshold = 1;
    
    public Goal(double x, double y, int w, int h){
        super(x,y,w,h);
    }

    public void drawImageForm(Graphics2D g){
        try{
            g.drawImage(ImageIO.read(new File("resources/goal.png")), (int)xpos-4, (int)ypos-2, null);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void drawImageForm(Graphics2D g,double xOffset) throws IOException{
            g.drawImage(ImageIO.read(new File("resources/portalexterior.png")), (int)(xpos-4-xOffset), (int)ypos-2, null);
    }
    public void update(){
    }
}
