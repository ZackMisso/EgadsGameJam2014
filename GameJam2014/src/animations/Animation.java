package animations;
import java.awt.Image;
import java.util.ArrayList;
public class Animation {
    private ArrayList<Image> images;
    private int index;
    private int timer;
    private int cnt;
    
    public Animation(ArrayList<Image> img){
        this(0,0,img);
    }
    
    public Animation(int i,int t, ArrayList<Image> img){
        images=img;
        index=i;
        timer=t;
        cnt=timer;
    }
    
    public void update(){
        cnt--;
        //System.out.println(cnt);
        if(cnt<0){
            if(++index==images.size())
                index=0;
            cnt=timer;
        }
    }
    
    public void reset(){
        index=0;
        cnt=timer;
    }
    
    public Image getCurrentImage(){
        return images.get(index);
    }
}
