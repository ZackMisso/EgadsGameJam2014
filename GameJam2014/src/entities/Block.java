package entities;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Block extends GameEntity{
    private Image light;
    private Image dark;
    private Image current;
    private boolean playerAbove;
    
    public Block(Color c,double x,double y,int w,int h){
        super(c,x,y,w,h);
        playerAbove=false;
        update();
        //c=Color.BLUE;
        if(c==Color.BLUE)
            initBlueImages();
        if(c==Color.RED)
            initRedImages();
        if(c==Color.GREEN)
            initGreenImages();
    }
    
    // used for main menu
    public void changeColor(){
        if(color==Color.BLUE)
            color=Color.RED;
        else if(color==Color.RED)
            color=Color.GREEN;
        else
            color=Color.BLUE;
        if(color==Color.BLUE)
            initBlueImages();
        if(color==Color.RED)
            initRedImages();
        if(color==Color.GREEN)
            initGreenImages();
    }
    
    private void initBlueImages(){
        try{
            dark=ImageIO.read(new File("resources/darkBlueTile.png"));
            light=ImageIO.read(new File("resources/lightBlueTile.png"));
            current=dark;
        }
        catch(IOException e) {System.out.println("Could Not Read Images");}
    }
    
    private void initRedImages(){
        try{
            dark=ImageIO.read(new File("resources/darkRedTile.png"));
            light=ImageIO.read(new File("resources/lightRedTile.png"));
            current=dark;
        }
        catch(IOException e) {System.out.println("Could Not Read Images");}
    }
    
    private void initGreenImages(){
        try{
            dark=ImageIO.read(new File("resources/darkGreenTile.png"));
            light=ImageIO.read(new File("resources/lightGreenTile.png"));
            current=dark;
        }
        catch(IOException e) {System.out.println("Could Not Read Images");}
    }
    
    public void update(){
        cxpos=xpos-1;//setCXpos(getXpos()-1);
        cypos=ypos-1;//setCYpos(getYpos()-1);
        cwidth=width-1;//setCWidth(getWidth()-1);
        cheight=height-1;//setCHeight(getHeight()-1);
        //width=32;
        //System.out.println(width);
    }
    
    public void checkImage(){
        if(playerAbove)
            current=light;
        else
            current=dark;
        playerAbove=false;
    }
    
    public void drawImageForm(Graphics2D g){
        g.drawImage(current,(int)xpos,(int)ypos, null);
    }
    
    // getter methods
    public boolean getPlayerAbove(){return playerAbove;}
    
    // setter methods
    public void setPlayerAbove(boolean param){playerAbove=param;}
}