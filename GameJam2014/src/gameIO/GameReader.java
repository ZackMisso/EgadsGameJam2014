package gameIO;
import gameState.GameState;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class GameReader{
	private int[][] tiles;
	public GameReader(GameState state){
		loadLevel(state);
	}
        
        private void loadLevel(GameState state){
            try{
                Scanner scan = new Scanner(new File(state.leveldata));
                //System.out.println("TEST :: GameReader");
                //int width = scan.nextInt();
                //int height = scan.nextInt();
                //NOTE: the dimensions of EVERY map should be 25 x 25 tiles
                tiles = new int[25][25];
                for(int row = 0; row<25; row++)
                {
                    for(int col = 0; col<25; col++){
                        tiles[row][col] = scan.nextInt();
                    }
                }
            }
            catch(FileNotFoundException e){System.out.println("ERROR File not found!! :: GameReader 28" + e);}
        }
        
        public int[][] getTiles(){return tiles;}
        
        
}