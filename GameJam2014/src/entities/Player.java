package entities;
import events.JumpEvent;
import events.LandedEvent;
import events.MoveLeftEvent;
import events.MoveRightEvent;
import events.StopEvent;
import events.PortalHit;
import entities.obstacles.Bridge;
import entities.obstacles.Lever;
import entities.traps.Gunner;
import entities.traps.Spikes;
import gameState.GameStateManager;
import gameState.DimensionState;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
public class Player extends GameEntity{
    private ArrayList<Follower> followers;
    private boolean right;
    private boolean left;
    private boolean jump;
    private boolean hitTop;
    //private boolean hitBot;
    private boolean hitRight;
    private boolean hitLeft;
    private boolean canSwap = false; //determines whether or not the player can swap between dimensions, based on portals
    private boolean fJump;
    private boolean fRight;
    private boolean fLeft;
    private boolean fStop;
    private boolean fLand;
    private boolean img;
    private Image one;
    private Image two;
    private Image current;
    private int deaths;
    private int imageCnt;
    private boolean flip;
    
    private DimensionState firstDimension;
    private DimensionState secondDimension;
    
    public Player(){
        this(0,0,0,0);
    }
    
    public Player(double x,double y,int w,int h){
        super(x,y,w,h);
        followers=new ArrayList<>();
        right=false;
        left=false;
        jump=false;
        flip=false;
        falling=true;
        deaths=0;
        initRedImages();
    }
    
    public void initRedImages(){
        try{
            System.out.println("ReadingPlayerImages");
            one=ImageIO.read(new File("resources/playerRED.png"));
            two=ImageIO.read(new File("resources/playerRED2.png"));
            current=one;
        }
        catch(IOException e) {System.out.println("Could Not Read Images");}
    }
    
    public void initBlueImages(){
        try{
            one=ImageIO.read(new File("resources/playerBLUE.png"));
            two=ImageIO.read(new File("resources/playerBLUE2.png"));
            current=two;
        }
        catch(IOException e) {System.out.println("Could Not Read Images");}
    }
    
    public void imageTransition(){
        if(img){
            initBlueImages();
            img=false;
        }else{
            initRedImages();
            img=true;
        }
    }
    
    public void imageUpdate(){
        if(--imageCnt==0){
            imageCnt=7;
            if(current==one)
                current=two;
            else
                current=one;
        }
    }
    
    public void update(){
        imageUpdate();
        if(falling){
            dy+=.11;
            if(hitTop){
                dy=0;
            }
            hitTop=false;
        }
        if(!(right^left)){
            fStop=true;
            dx=0;
        }
        else if(right&&!hitRight){
            hitLeft=false;
            fRight=true;
            if(dx<2)
                dx+=0.3;
        }
        else{
            if(!hitLeft){
                fLeft=true;
                if(dx>-2)
                    dx-=0.3;
            }
            if(left)
                hitRight=false;
        }
        if(jump){
            fJump=true;
            ypos-=2;
            dy=-4.5;
        }
        if(hitRight||hitLeft)
            dx=0;
        if(hitTop)
            dy=0;
        jump=false;
        xtemp=xpos+dx;
        ytemp=ypos+dy;
        checkCollisionsWithBlocks(gs.getBlocks(),gs);
        checkCollisionsWithPortals(gs.getPortals(),gs.getGSM());
        checkCollisionsWithFollowers(gs.getFollowers(),gs);
        checkCollisionsWithLevers(gs.getLevers(),gs);
        checkCollisionsWithSpikes(gs.getSpikes(),gs);
        checkCollisionsWithGunners(gs.getGunners(),gs);
        if(gs.getGoal()!=null)
            checkCollisionsWithGoal(gs.getGoal(), gs);
        updateFollowers();
        //gs.setXOffset(gs.getXOffset()+xtemp-xpos);
        xpos=xtemp;
        ypos=ytemp;
    }
    
    private void updateFollowers(){
        for(int i=0;i<followers.size();i++){
            if(followers.get(i).getDimension().equals(gs)){
                if(fJump&&followers.get(i).getPlayerControlled()){
                    followers.get(i).getEvents().add(new JumpEvent(xpos,ypos));
                }
                if(fRight&&followers.get(i).getPlayerControlled()){
                    followers.get(i).getEvents().add(new MoveRightEvent(xpos,ypos));
                }
                if(fLeft&&followers.get(i).getPlayerControlled()){
                    followers.get(i).getEvents().add(new MoveLeftEvent(xpos,ypos));
                }
                if(fStop&&followers.get(i).getPlayerControlled()){
                    followers.get(i).getEvents().add(new StopEvent(xpos,ypos));
                }
                if(fLand&&followers.get(i).getPlayerControlled()){
                    followers.get(i).getEvents().add(new LandedEvent(xpos,ypos));
                }
                resetFollowerVariables();
            }
        }
    }
    
    private void resetFollowerVariables(){
        fJump=false;
        fRight=false;
        fLeft=false;
        fStop=false;
        fLand=false;
    }
    
    // This will soon not be needed
    public void checkCollisionsWithBlocks(ArrayList<Block> list, DimensionState state){
        falling=true;
        if(list == null) //safety check
            return;
        double tempDx=dx;
        double tempX=xtemp;
        double xOffset=state.getXOffset();
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
                        //System.out.println(dy);
                        if(dy>0){
                            fLand=true;
                            dy=0;
                        }
                        ytemp=b.getYpos()-height;
                        falling=false;
                        //System.out.println("WHY NOT RUNNING");
                        b.setPlayerAbove(true);
                        //System.out.println(b.getPlayerAbove());
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
                            }else
                                hitLeft=true;
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
                                hitRight=true;
                            }
                        }
                    }else{
                        // Collision on the bottom
                        if(dy<0){
                            dy=0;
                        }
                        if(xLengthDistance(b)<16)
                            hitTop=true;
                        ytemp=list.get(i).getYpos()+list.get(i).height+1;
                    }
                }
            }
        }
    }
    public void checkCollisionsWithPortals(ArrayList<Portal> list, GameStateManager gsm){
        for(Portal p: list){
            if(Math.abs(getRelativeX(p))<width/2+p.getWidth()/2&&Math.abs(getRelativeY(p))<height/2+p.getHeight()/2){
                if(followers.size()>0){
                    Follower temp=followers.get(0);
                    if(temp.distanceBetweenCenters(p)<20.0){
                        p.consumeFollower(temp);
                    }
                    System.out.println(temp.distanceBetweenCenters(p));
                }
                //for(int i = p.getThreshold(); !followers.isEmpty()&&i>0; i--){
                //    Random rand = new Random();
                //    int index = rand.nextInt(followers.size());
                //    Follower temp = followers.get(index);
                //    //temp.getEvents().add(new PortalHit(p,xpos,ypos));
                //    //p.consumeFollower(temp);
                //}
                if(p.getThreshold()<=0&&p.getDimensionID()>=gsm.getNumDimensions()&&!p.fired()){
                    p.fire();
                    //System.out.println("THIS RAN");
                    gsm.loadDimension(p.getDimensionID(), Color.BLUE);
                    canSwap = true;
                }
                //setDimension(gsm.getDimension(p.getDimensionID()));
                //System.out.println("There was a collision :: Player");
                return;
                //System.out.println("There was a collision");
            }
        }
    }
    public void checkCollisionsWithFollowers(ArrayList<Follower> list,DimensionState state){
        //System.out.println("RAN :: Player");
        for(int i=0;i<list.size();i++){
            if(Math.abs(getRelativeX(list.get(i)))<width/2+list.get(i).getWidth()/2&&Math.abs(getRelativeY(list.get(i)))<height/2+list.get(i).getHeight()/2){
                list.get(i).setPlayerControlled(true);
                list.get(i).setHolder(this);
                //System.out.println("There was a collision :: Player");
                return;
                //System.out.println("There was a collision");
            }
        }
    }
    public void checkCollisionsWithLevers(ArrayList<Lever> list,DimensionState state){
        for(int i=0;i<list.size();i++){
            if(Math.abs(getRelativeX(list.get(i)))<width/2+list.get(i).getWidth()/2&&Math.abs(getRelativeY(list.get(i)))<height/2+list.get(i).getHeight()/2){
                if(!list.get(i).getActivated())
                    list.get(i).activate();
                return;
            }
        }
    }
    public void checkCollisionsWithSpikes(ArrayList<Spikes> list,DimensionState state){
        for(int i=0;i<list.size();i++){
            if(Math.abs(getRelativeX(list.get(i)))<width/2+list.get(i).getWidth()/2&&Math.abs(getRelativeY(list.get(i)))<height/2+list.get(i).getHeight()/2){
                die(state);
                update();
                System.out.println("HIT SPIKES");
                return;
            }
        }
    }
    public void checkCollisionsWithGunners(ArrayList<Gunner> list,DimensionState state){
        for(int i=0;i<list.size();i++){
            if(Math.abs(getRelativeX(list.get(i)))<width/2+list.get(i).getWidth()/2&&Math.abs(getRelativeY(list.get(i)))<height/2+list.get(i).getHeight()/2){
                die(state);
                return;
            }
        }
    }
    
    public void checkCollisionsWithGoal(Goal g, DimensionState state){
        if(Math.abs(getRelativeX(g))<width/2+g.getWidth()/2 && Math.abs(getRelativeY(g))<height/2+g.getHeight()/2){
            System.out.println("You win! Deaths: " + deaths);
            System.exit(0);
        }
    }
    public void reset(){
        dx = 0;
        dy = 0;
        right = false;
        left = false;
        jump = false;
    }

    public void die(DimensionState state){
        deaths++;
        state.getGSM().reset();
    }

    public void keyPressed(int key){
        if(key==KeyEvent.VK_D)right=true;
        if(key==KeyEvent.VK_A)left=true;
        if(key==KeyEvent.VK_SPACE&&!falling)jump=true;
    }
    
    public void keyReleased(int key){
        if(key==KeyEvent.VK_D)right=false;
        if(key==KeyEvent.VK_A)left=false;
    }

    public void drawRectForm(Graphics2D g){
        super.drawRectForm(g);

        if(firstDimension==null&&secondDimension==null){
            //System.out.println("Dimensions are null :: Player");
            return;
        }
        if(firstDimension==null){
            g.setColor(secondDimension.getColor());
            g.fillRect((int)xpos+1,(int)ypos+1,width-1,height-1);
            //System.out.println("firstNull :: Player");
        }
        else if(secondDimension==null){
            g.setColor(firstDimension.getColor());
            g.fillRect((int)xpos+1,(int)ypos+1,width-1,height-1);
            //System.out.println("secondNull :: Player");
        }else{
            //if(firstDimension.getColor()==Color.BLUE){
            //    //System.out.println("First is BLUE");
            //}
            //if(secondDimension.getColor()==Color.RED){
            //    //System.out.println("Second is RED");
            //}
            g.setColor(firstDimension.getColor());
            //g.setColor(secondDimension.getColor());
            int x=(int)xpos+1;
            int y=(int)ypos+1;
            g.fillRect(x,y,width/2,height-1);
            g.setColor(secondDimension.getColor());
            x=(int)xpos+width/2;
            y=(int)ypos+1;
            g.fillRect(x,y,width/2,height-1);
        }
    }
    
    public void drawImageForm(Graphics2D g){
        g.drawImage(current,(int)xpos,(int)ypos,null);
    }
    
    public void power(){canSwap=true;}
    public boolean canswap(){return canSwap;}
    public void setDimension(DimensionState newdimension){
        if(flip&&gs.getGSM().getActive()!=firstDimension){
            firstDimension=newdimension;
            
            //System.out.println("First Set :: Player");
        }
        else{
            if(gs.getGSM().getActive()==secondDimension)
                firstDimension=newdimension;
            secondDimension=newdimension;
            //System.out.println("Second Set :: Player");
        }
        flip = !flip;
    }
    public ArrayList<Follower> getFollowers(){return followers;}
    public int getNumDeaths(){return deaths;}

    public void setNumDeaths(int param){deaths=param;}
}