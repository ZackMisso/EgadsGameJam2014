package entities;
import events.Event;
import entities.obstacles.Bridge;
import gameState.DimensionState;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
public class Follower extends GameEntity{
    private ArrayList<Event> events; // maybe make a stack?
    private Player holder;
    //private Event current;
    private boolean right;
    private boolean left;
    private boolean jump;
    private boolean playerControlled;
    private double respawnx;
    private double respawny;
    
    public Follower(double x,double y,int w,int h){
        this(Color.YELLOW, x, y, w, h);
    }
    
    public Follower(Color c,double x,double y,int w,int h){
        super(c,x-128,y+80,w,h);
        respawnx = x; 
        respawny = y;
        holder=null;
        events=new ArrayList<>();
        //current=null;
        right=false;
        left=false;
        jump=false;
        falling=false;
        playerControlled=false; // debugging
    }
    
    public void update(){
        if(falling){
            dy+=.11;
            //if(hitTop){
            //    dy=0;
            //}
            //hitTop=false;
        }
        if(ypos>800){
            die();
            return;
        }
        if(!(right^left))
            dx=0;
        else if(right){
            //hitLeft=false;
            if(dx<2)
                dx+=0.3;
        }
        else{
            //if(!hitLeft)
            if(dx>-2)
                dx-=0.3;
            //if(left)
            //    hitRight=false;
        }
        if(jump){
            //System.out.println("JUMPs");
            ypos-=2;
            dy=-4.5;
        }
        //System.out.println(dy);
        //if(hitRight||hitLeft)
        //    dx=0;
        //if(hitTop)
        //    dy=0;
        jump=false;
        
        if(playerControlled)
            updateByEvent();
        else{
            // player does not have
        }
        
        //System.out.println(events.size());
        
        xtemp=xpos+dx;
        ytemp=ypos+dy;
        //checkMovementOnSurroundings(); // comment to turn off tilePhysics
        if(holder!=null)
            checkCollisionsWithBlocks(holder.gs.getBlocks(),holder.gs);
        xpos=xtemp;
        ypos=ytemp;
    }
    
    public void checkCollisionsWithBlocks(ArrayList<Block> list,DimensionState state){
        falling=true;
        if(list == null) //safety check
            return;
        double tempDx=dx;
        double tempX=xtemp;
        for(int i=0;i<list.size();i++){
            Block b = list.get(i);
            if(b==null)
                continue;
            double w=.5*(b.cwidth+width);
            double h=.5*(b.cheight+height);
            double dxx=b.getCCenterX()-getCenterX();
            double dyy=b.getCCenterY()-getCenterY();
            if(Math.abs(dxx)<=w&&Math.abs(dyy)<=h){
                double wy=w*dyy;
                double hx=h*dxx;
                if(wy>hx){
                    if(wy>-hx){
                        // Collision from the top
                        if(dy>0){
                            dy=0;
                        }
                        ytemp=b.getYpos()-height;
                        falling=false;
                        b.setPlayerAbove(true);
                    }else{
                        // Collision from the left
                        if(!(b instanceof Bridge)){
                            if(dx<0){
                                dx=0;
                            }
                            xtemp=b.xpos+b.width;
                            if(lengthDistance(b)>16){
                                dx=tempDx;
                                xtemp=tempX;
                            }
                        }
                    }
                }else{
                    if(wy>-hx){
                        // Collision on the right
                        if(!(b instanceof Bridge)){
                            if(dx>0){
                                dx=0;
                            }
                            xtemp=list.get(i).getXpos()-width-1;
                            if(lengthDistance(b)>16){
                                dx=tempDx;
                                xtemp=tempX;
                            }
                            else{
                                //hitRight=true;
                            }
                        }
                    }else{
                        // Collision on the bottom
                        if(dy<0){
                            dy=0;
                        }
                        ytemp=list.get(i).getYpos()+list.get(i).height+1;
                    }
                }
            }
        }
    }
    
    public void paint(Graphics2D g){
        drawRectForm(g);
    }
    
    private void die(){
        events.clear();
        playerControlled=false;
        xpos = respawnx; ypos = respawny;
        dx = 0; dy = 0;
    }
    
    public void delete(){
        gs.getFollowers().remove(this);
        holder.getFollowers().remove(this);
    }
    
    public void updateByEvent(){
        if(!events.isEmpty()){
            Event event=events.get(0);
            if(event.shouldFire(this))
                event.fire(this);
            events.remove(0);
        }else{
            // humor possibly
        }
    }
    
    public void drawImageForm(Graphics2D g){
        // implement
    }
    
    // getter methods
    public ArrayList<Event> getEvents(){return events;}
    public boolean getRight(){return right;}
    public boolean getLeft(){return left;}
    public boolean getJump(){return jump;}
    public boolean getPlayerControlled(){return playerControlled;}
    public Player getHolder(){return holder;}
    
    // setter methods
    public void setRight(boolean param){right=param;}
    public void setLeft(boolean param){left=param;}
    public void setJump(boolean param){jump=param;}
    public void setPlayerControlled(boolean param){playerControlled=param;}
    public void setDx(double param){dx=param;}
    public void setHolder(Player param){holder=param;}
}