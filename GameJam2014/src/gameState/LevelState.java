package gameState;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
public class LevelState {
    private ArrayList<DimensionState> states;
    private GameStateManager gsm;
    private DimensionState current;
    private String file;
    
    public LevelState(GameStateManager param2,String param){
        file=param;
        gsm=param2;
        states=new ArrayList<>();
        current=null;
        loadDimensions();
    }
    
    public void loadDimensions(){
        try{
            Scanner scan=new Scanner(new File(file));
            Color color=Color.RED;
            while(scan.hasNextLine()){
                DimensionState state=new DimensionState(color,gsm,scan.nextLine());
                if(color==Color.RED)
                    color=Color.BLUE;
                else if(color==Color.BLUE)
                    color=Color.GREEN;
                states.add(state);
            }
        }catch (IOException e) {}
    }
    
    public void transition(int dest){
        // implement
    }
    
    public void update(){
        current.update();
    }
    
    public void draw(Graphics2D g){
        current.draw(g);
    }
    
    public ArrayList<DimensionState> getDimensions(){
        return states;
    }
}
