package core;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyboardListener implements KeyListener{
    private GameThread reference;
	//private final boolean[] keys; // the list of keys that could be pressed
	
	public KeyboardListener(GameThread param){
		//keys=new boolean[256];
        reference=param;
	}
	
    @Override
	// what happens when a key is pressed
	public void keyPressed(KeyEvent event){
		//keys[event.getKeyCode()]=true;
        reference.getGSM().keyPressed(event.getKeyCode());
   	}
	
    @Override
	// what happens when a key is released
	public void keyReleased(KeyEvent event){
		//keys[event.getKeyCode()]=false;
        reference.getGSM().keyReleased(event.getKeyCode());
	}
	
    @Override
	// what happens when a key is typed
	public void keyTyped(KeyEvent event){
		// does nothing for now
	}
	
    @Override
	// toString method included for the testing system
	public String toString(){
		return "";
		// implement
	}
	
	// returns if a specified key is being pressed
	//public boolean isKeyPressed(int key){return keys[key];}
	// returns if a specified key is not being pressed
	//public boolean isKeyReleased(int key){return !keys[key];}
}
