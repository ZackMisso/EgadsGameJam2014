package entities;
import core.GameThread;
//import gameState.GameState;
import gameState.DimensionState;
//import datastructures.DoubleCoordinate;
//import fuckingTiles.TileMap;
//import fuckingTiles.TileRect;
import java.awt.Color;
import java.awt.Graphics2D;
public abstract class GameEntity {
    //protected TileMap map;
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
    
    public GameEntity(double x,double y,int w,int h){
        this(Color.GREEN,x,y,w,h);
    }
    
    public GameEntity(Color c,double x,double y,int w,int h){
        color=c;
        xpos=x;
        ypos=y;
        width=w;
        height=h;
        onScreen=false;
        falling=true;
    }
    
    public abstract void update();
    
    public boolean isOnScreen(DimensionState state){
        if(xpos+width-state.getXOffset()<0||xpos-state.getXOffset()>GameThread.WIDTH){
            onScreen=false;
            return false;
        }
        if(ypos+height-state.getYOffset()<0||ypos-state.getYOffset()>GameThread.HEIGHT){
            onScreen=false;
            return false;
        }
        onScreen=true;
        return true;
    }
    
    public double getCenterX(){
        //System.out.println("asdfasdfasd "+xpos);
        return xpos+width/2;
    }
    
    public double getCenterY(){
        return ypos+height/2;
    }
    
    public double getCCenterX(){
        return cxpos+cwidth/2;
    }
    
    public double getCCenterY(){
        return cypos+cheight/2;
    }
    
    public double getRelativeX(GameEntity other){
        if(other == null){
            System.out.println("THIS SHOULD NOT HAPPEN: GameEntity :: 84");
            return Double.MAX_VALUE;
        }
        return getCenterX()-other.getCenterX();
    }

    public double getRelativeY(GameEntity other){
        return getCenterY()-other.getCenterY();
    }
    
    public double distanceBetweenCenters(GameEntity other){
        double x=getCenterX()-other.getCenterX();
        double y=getCenterY()-other.getCenterY();
        x*=x;
        y*=y;
        return Math.sqrt(x+y);
    }
    
    public double distanceBetween(double x,double y){
        x-=xpos;
        y-=ypos;
        x*=x;
        y*=y;
        return Math.sqrt(x+y);
    }

    public double efficientDistanceBetween(GameEntity other){
        double x=getCenterX()-other.getCenterX();
        double y=getCenterY()-other.getCenterY();
        x*=x;
        y*=y;
        return x+y;
    }
    
    public double getMaxDistance(GameEntity other){
        double x=width/2+other.width/2;
        double y=height/2+other.height/2;
        x*=x;
        y*=y;
        return Math.sqrt(x+y);
    }
    
    public double lengthDistance(GameEntity other){
        double y=getCenterY()-other.getCCenterY();
        return Math.abs(y);
    }
    
    public double xLengthDistance(GameEntity other){
        double x=getCenterX()-other.getCCenterX();
        return Math.abs(x);
    }
    
    public void drawRectForm(Graphics2D g){
        g.setColor(color);
        g.drawRect((int)xpos,(int)ypos,width,height);
    }
    
    public void drawRectForm(Graphics2D g,double xOffset){
        g.setColor(color);
        g.drawRect((int)(xpos+xOffset),(int)ypos,width,height);
    }
    
    public abstract void drawImageForm(Graphics2D g);
    
    // GETTER METHODS
    public void setPosition(int x, int y){
        xpos = x; ypos = y;
    }
    public double getXpos(){return xpos;}
    public double getYpos(){return ypos;}
    public int getCHeight(){return cheight;}
    public int getCWidth(){return cwidth;}
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public boolean isFalling(){return falling;}
    public DimensionState getDimension(){return gs;}
    
    // SETTER METHODS
    public void setReference(DimensionState param){
        gs = param;
    }
    //public void setMap(TileMap param){map=param;}
    public void setXpos(double param){xpos=param;}
    public void setYpos(double param){ypos=param;}
}
