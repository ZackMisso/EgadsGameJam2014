package gameState;
import fuckingTiles.TileMap;
import java.awt.Graphics2D;
public abstract class GameState {
    protected GameStateManager gsm;
    protected boolean on;
    private int id;
    public final String leveldata;
    protected TileMap tilemap;
    
    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
    public abstract void reset();
    
    //constructor
    public GameState(String name){
        leveldata = name;
        id=-100;
    }
    
    // getter methods
    public GameStateManager getGSM(){return gsm;}
    public int getId(){return id;}
    public boolean isOn(){return on;}
    
    // setter methods
    public void setGSM(GameStateManager param){gsm=param;}
    public void setID(int param){id=param;}
    public void setOn(boolean param){on=param;}
}
