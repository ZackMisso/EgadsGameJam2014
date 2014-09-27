package gameState;
import entities.Player;
import java.awt.Color;
import java.awt.Graphics2D;
public class GameHeader {
    private Player reference;
    
    public GameHeader(Player param){
        reference=param;
    }
    
    public void draw(Graphics2D g){
        String score="Number of Deaths :: "+reference.getNumDeaths();
        g.setColor(Color.WHITE);
        g.drawString(score,20,780);
    }
}
