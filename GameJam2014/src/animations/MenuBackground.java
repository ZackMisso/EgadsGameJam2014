package animations;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics2D;
public class MenuBackground {
    private ArrayList<Star> stars;
    private Random random;
    
    public MenuBackground(){
        stars=new ArrayList<>();
        random=new Random();
        initStars();
    }
    
    private void initStars(){
        for(int i=0;i<35;i++)
            stars.add(new Star(random.nextInt(800),random.nextInt(688),random.nextInt(80)));
    }
    
    public void update(){
        for(int i=0;i<stars.size();i++)
            if(stars.get(i).update())
                stars.set(i,new Star(random.nextInt(800),random.nextInt(688),80));
    }
    
    public void draw(Graphics2D g){
        for(int i=0;i<stars.size();i++)
            stars.get(i).draw(g);
    }
}
