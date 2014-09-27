package core;
import javax.swing.JFrame;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Game{
    //private final MainMenu menu;
	private final JFrame window; // the window for the game
	private final GameThread gameThread; // the game thread
	private final KeyboardListener keyListener; // reads key input
	private final MousepadListener mouseListener; // reads mouse input

	public Game(int x,int y,String title){
        //menu=new MainMenu();
		window=new JFrame();
		window.setSize(x,y);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);
		gameThread=new GameThread(this);
		keyListener=new KeyboardListener(gameThread);
		mouseListener=new MousepadListener(gameThread); // i Don't know if this is needed
		window.add(gameThread);
		window.addKeyListener(keyListener);
		window.addMouseListener(mouseListener);
        muzak();
		new Thread(gameThread).start();
	}
    
    @Override
    public String toString(){
        return "Game was made.... Good Luck";
    }
    
    public void muzak() {
	try{
		//AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("glebstar.wav"));
		//Clip clip = AudioSystem.getClip();
		//clip.open(audioInputStream);
		//clip.start();
	}
    catch(Exception ex)
		{System.err.println(ex);}
    }

	// getter methods
	public JFrame getWindow(){return window;}
	public KeyboardListener getKeyListener(){return keyListener;}
	public MousepadListener getMouseListener(){return mouseListener;}
	public GameThread getGameThread(){return gameThread;}
}