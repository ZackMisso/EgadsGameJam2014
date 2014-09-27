package gameState;
import animations.MenuBackground;
import animations.MenuTitle;
import entities.Block;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
public class MainMenu extends GameState{
    private GameStateManager gsm;
    private ArrayList<DimensionState> states;
    private ArrayList<Block> blocks;
    private MenuBackground background;
    private MenuTitle title;
    private int switchTimer;
    private int index;
    
    public MainMenu(GameStateManager param,String name){
        super(name);
        gsm=param;
        init();
    }
    
    public void init(){
        blocks=new ArrayList<>();
        index=0;
        background=new MenuBackground();
        title=new MenuTitle(50,100);
        switchTimer=250;
        states=new ArrayList<>(); 
        for(int i=0;i<26;i++){
            blocks.add(new Block(Color.BLUE,i*32,760,32,32));
            //blocks.add(new Block(Color.RED,i*32,728,32,32));
            //blocks.add(new Block(Color.GREEN,i*32,696,32,32));
        }
        for(int i=0;i<26;i++)
            blocks.add(new Block(Color.RED,i*32,728,32,32));
        for(int i=0;i<26;i++)
            blocks.add(new Block(Color.GREEN,i*32,696,32,32));
    }
    
    public void update(){
        background.update();
        turnOff();
        blocks.get(switchTimer--/10).setPlayerAbove(true);
        blocks.get(switchTimer/10).checkImage();
        blocks.get(switchTimer/10+26).setPlayerAbove(true);
        blocks.get(switchTimer/10+26).checkImage();
        blocks.get(switchTimer/10+52).setPlayerAbove(true);
        blocks.get(switchTimer/10+52).checkImage();
        if(switchTimer==0){
            for(int i=0;i<blocks.size();i++)
                blocks.get(i).changeColor();
            switchTimer=250;
        }
    }

    public void turnOff(){
        for(int i=0;i<blocks.size();i++){
            blocks.get(i).setPlayerAbove(false);
            blocks.get(i).checkImage();
        }
    }
    
    public void draw(Graphics2D g){
        background.draw(g);
        title.draw(g);
        for(int i=0;i<blocks.size();i++)
            blocks.get(i).drawImageForm(g);
        //states.get(index).draw(g);
    }
    
    public void keyPressed(int k){
        gsm.endMainMenu();
    }
    
    public void keyReleased(int k){
        // will do nothing
    }
    
    public void reset(){
        // will do nothing
    }
}