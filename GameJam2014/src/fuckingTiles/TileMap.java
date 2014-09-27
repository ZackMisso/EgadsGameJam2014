package fuckingTiles;
import java.awt.Graphics2D;
import java.awt.Color;
public class TileMap {
    private double startX;
    private double startY;
    private int[][] map;
    private int tileSize;
    
    public TileMap(int tileSize,int[][] mp){
        this(0,0,tileSize,mp);
    }

    public TileMap(double x,double y,int tS,int[][] mp){
        startX=x;
        startY=y;
        tileSize=tS;
        map=mp;
    }
    
    public int getType(int row,int col){
        
        ////int rc=map[row][col];
        ////int r=rc/numTilesAcross;
        //int c=rc%numTilesAcross;
        //return tiles[row][col].getType();
        return 0;
    }
    
    public void draw(Graphics2D g){
        g.setColor(Color.GREEN);
        for(int i=0;i<map.length;i++){
            for(int f=0;f<map[0].length;f++){
                if(map[i][f]>0)
                    g.drawRect(i*tileSize,f*tileSize,tileSize,tileSize);
            }
        }
    }

    public int[][] getMap(){return map;}
    public int getWidth(){return map[0].length;}
    public int getHeight(){return map.length;}
    public int getTileSize(){return tileSize;}
    
    // setter methods
    public void setStartX(double param){startX=param;}
    public void setStartY(double param){startY=param;}
}