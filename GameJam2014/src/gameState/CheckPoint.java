package gameState;
public class CheckPoint {
    private DimensionState dimState;
    private double xpos;
    private double ypos;
    
    public CheckPoint(DimensionState param,double x,double y){
        dimState=param;
        xpos=x;
        ypos=y;
    }
    
    // getter methods
    public DimensionState getDimState(){return dimState;}
    public double getXpos(){return xpos;}
    public double getYpos(){return ypos;}
}
