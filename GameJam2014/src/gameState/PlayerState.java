package gameState;
import java.util.ArrayList;
public class PlayerState {
    private ArrayList<CheckPoint> checkPoints;
    private int deaths;
    
    public PlayerState(){
        checkPoints=new ArrayList<>();
        deaths=0;
    }
    
    public void createCheckPoint(DimensionState current,double x,double y){
        for(int i=0;i<checkPoints.size();i++)
            if(checkPoints.get(i).getDimState().getId()==current.getId())
                checkPoints.remove(i--);
        checkPoints.add(new CheckPoint(current,x,y));
    }
    
    // getter methods
    public ArrayList<CheckPoint> getCheckPoints(){return checkPoints;}
    public int getDeaths(){return deaths;}
}
