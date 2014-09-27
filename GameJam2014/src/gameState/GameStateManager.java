package gameState;
import core.GameThread;
import entities.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
public class GameStateManager {
    private ArrayList<DimensionState> dimensions;
    //private DimensionState dimensions.get(0);
    //private DimensionState dimensions.get(1);
    private DimensionState active;
    private DimensionState inactive;
    private MainMenu menu;
    private GameHeader scoreboard;
    private int idCnt = 0;
    private Player protagonist;
    //private GameThread currentthread;
    
    public GameStateManager(GameThread thread){
        startMainMenu();
        
        //protagonist = new Player(0,0,16,16);
        //dimensions = new ArrayList<>();
        //dimensions.add(new DimensionState(Color.RED,this,"resources/dimension1data.txt"));
        //dimensions.add(new DimensionState(Color.BLUE,this,"resources/dimension1data.txt"));
        //dimensions.get(1).setPlayer(protagonist);
        //scoreboard=new GameHeader(protagonist);
        //active = dimensions.get(0);
        //active.setOn(true);
        //active.setPlayer(protagonist);     
        //active.setID(idCnt++);
        //protagonist.setDimension(active);
        //dimensions.get(1).setID(idCnt++);
        //currentthread = thread;
    }

    public void startMainMenu(){
        menu=new MainMenu(this,"MainMenu");
        menu.setOn(true);
        //endMainMenu();
    }

    public void endMainMenu(){
        System.out.println("SHOULD WORK");
        menu.setOn(false);
        protagonist = new Player(0,0,16,16);
        dimensions = new ArrayList<>();
        dimensions.add(new DimensionState(Color.RED,this,"resources/dimension1data.txt"));
        scoreboard=new GameHeader(protagonist);
        active = dimensions.get(0);
        active.setOn(true);
        active.setPlayer(protagonist);     
        active.setID(idCnt++);
        protagonist.setDimension(active);
    }
    
    //Loads a new dimension into memory. Can load multiple instances of the same
    //level file.
    //@param i: specifies which level to load. c: the color of the new level
    public void loadDimension(int i, Color c)
    {
        inactive = new DimensionState(c, this, "resources/dimension"+(i+1)+"data.txt");
        dimensions.add(inactive);
        inactive.setPlayer(protagonist);
        inactive.setID(idCnt++);
        inactive.setOn(false);
        protagonist.setDimension(inactive);
        //System.out.println("Dimensions size :: "+dimensions.size());
    }
    public void transition(){
        if(!protagonist.canswap())
            return;
        DimensionState temp = active;
        active = inactive;
        inactive = temp;
        active.setOn(true);
        inactive.setOn(false);
        protagonist.setReference(active);
        /*if(dimensions.get(0).isOn()){
            dimensions.get(0).setOn(false);
            dimensions.get(1).setOn(true);
            protagonist.setReference(dimensions.get(1));
        }
        else if(dimensions.get(1).isOn()){
            dimensions.get(0).setOn(true);
            dimensions.get(1).setOn(false);
            protagonist.setReference(dimensions.get(0));
        }*/
    }
    
    public void update(){
        if(menu.isOn())
            menu.update();
        else
            if(active!=null)
                active.update();
    }
    
    public void draw(Graphics2D g){
        if(menu.isOn())
            menu.draw(g);
        else{
            //scoreboard.draw(g);
            if(active!=null){
                //scoreboard.draw(g);
                active.draw(g);
                scoreboard.draw(g);
            }
        }
    }
    
    public void reset(){
        for(DimensionState d: dimensions){
            d.reset();
        }
    }
    
    
    public void keyReleased(int c){
        if(menu.isOn())
            menu.keyReleased(c);
        else
            active.keyReleased(c);
    }
    
    public void keyPressed(int c){
        if(menu.isOn())
            menu.keyPressed(c);
        else
            switch(c){
                case KeyEvent.VK_X: transition(); break;
                case KeyEvent.VK_R: reset(); break;
                default: active.keyPressed(c);
            }
    }
    
    public int getNumDimensions(){return idCnt;}
    public DimensionState getDimension(int index){
        return dimensions.get(index);
    }
    public DimensionState getActive(){return active;}
}
