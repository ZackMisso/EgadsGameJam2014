package gameState;

import entities.*;
import entities.obstacles.*;
import entities.traps.*;
import fuckingTiles.TileMap;
import gameIO.GameReader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public class DimensionState extends GameState {

    //public final int DimensionID;

    private ArrayList<Block> blocks;
    private ArrayList<Follower> followers;
    private ArrayList<Portal> portals;
    private ArrayList<Lever> levers;
    private ArrayList<Spikes> spikes;
    private ArrayList<Gunner> gunners;
    private ArrayList<Bridge> bridges;
    private Goal goal;
    
    private ArrayList<Dependent> depends;
    private Player player;
    private Color color;
    private double xOffset;
    private double yOffset;
    private double spawnX;
    private double spawnY;
//        private int playerX; //coordinates for player in this dimension
//        private int playerY;

    public DimensionState(Color c,GameStateManager gsm, String name) {
        super(name);
        color=c;
        setGSM(gsm);
        //color = Color.WHITE;
        init();
    }

    public void init() {
        //if(player==null)
        //    player = new Player(200,100,16,16);
        //player.setReference(this);
        blocks = new ArrayList<>();
        followers = new ArrayList<>();
        portals = new ArrayList<>();
        levers=new ArrayList<>();
        spikes = new ArrayList<>();
        gunners = new ArrayList<>();
        bridges = new ArrayList<>();
        depends=new ArrayList<>();
        //player.setPosition(16,16);
        xOffset = 0;
        yOffset = 0;
        initLevel();
        initEntities();
    }

    private void initLevel() {
        GameReader gr = new GameReader(this);
        tilemap = new TileMap(0, 0, 32, gr.getTiles());
        //player.setMap(tilemap);
        int size = tilemap.getTileSize();
        int[][] tiles = gr.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int f = 0; f < tiles[0].length; f++) {
                switch (tiles[i][f]/10) {
                    case 0: //floor tile
                        if(tiles[i][f]==1)
                            blocks.add(new Block(color, f * 32 + xOffset, i * 32 + yOffset, 32, 32));
                        break;
                    case 2: //player spawn
                        spawnX = f * size + xOffset;
                        spawnY = i * size + yOffset;
                        break;
                    case 3: //follower
                        Follower temp = new Follower(f * size + xOffset, i * size + yOffset, 16, 16);
                        temp.setHolder(player);
                        temp.setReference(this);
                        followers.add(temp);
                        break;
                    case 4: //portal
                        try {
                            portals.add(new Portal(f * size + xOffset, i * size + yOffset, 48, 48,tiles[i][f]%10));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            break;
                        }
                    case 5: //spike
                        Spikes tempspike = new Spikes(f * size + xOffset, i * size + yOffset+16, 16, 16);
                        Spikes tempspike2=new Spikes(f * size + xOffset+16, i * size + yOffset+16, 16, 16);
                        spikes.add(tempspike);
                        spikes.add(tempspike2);
                        tempspike.setReference(this);
                        tempspike2.setReference(this);
                        break;
                    case 6: //gunner
                        Gunner tempgunner = new Gunner(f * size + xOffset, i * size + yOffset, 96, 16);
                        tempgunner.setReference(this);
                        break;
                    case 7: //levers
                        Lever templever = new Lever(f*size+xOffset, i*size + yOffset+16, 16, 16);
                        templever.setReference(this);
                        levers.add(templever);
                        templever.setdependID(tiles[i][f]%10);
                        break;
                    case 8: //bridge
                        Bridge tempbridge = new Bridge(f*size+xOffset, i*size+yOffset, 96, 16 );
                        tempbridge.setReference(this);
                        bridges.add(tempbridge);
                        break;
                    case 9: //goal
                        goal = new Goal(f*size+xOffset, i*size+yOffset+5, 16, 16);
                        goal.setReference(this);
                        break;
                }
            }
        }
        for(Lever l: levers){
            l.setDependent(bridges.get(l.getdependID()));
        }
            //for(int i=0;i<followers.size();i++){
        //    System.out.println("THIS RAN");
        //    player.getFollowers().add(followers.get(i));
        //}
        //player.getFollowers().add(followers.get(0));
        //System.out.println(player.getFollowers().size());
    }

    private void initEntities() {

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawString("A: left", 25, 50);
        g.drawString("D: right", 25, 65);
        g.drawString("SPACE: jump", 25, 80);
        g.drawString("X: change dimensions", 25, 95);

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).drawImageForm(g);
        }
        player.drawRectForm(g);
        for (Follower f : followers) {
            f.drawRectForm(g, xOffset);
        }
        for (Portal p : portals) {
            p.drawImageForm(g, xOffset);
        }
        for(Lever l: levers){
            l.drawImageForm(g);
        }
        for(Spikes s: spikes){
            s.drawImageForm(g);
        }
        for(Gunner gun: gunners){
            gun.drawImageForm(g);
        }
        for(Bridge b: bridges){
            b.drawImageForm(g);
        }
        if(goal!=null)
            goal.drawImageForm(g);
    }

    public void updatePositions() {
        for (Follower f : followers) {
            f.setXpos(f.getXpos() - xOffset);
        }
        for (Portal p : portals) {
            p.setXpos(p.getXpos() - xOffset);
        }
        for (Block b : blocks) {
            b.setXpos(b.getXpos() - xOffset);
        }
        xOffset = 0;
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public void keyReleased(int k) {
        player.keyReleased(k);
    }

    public void reset() {
        //init();
        if (on) {
            System.out.println("Player was reset");
            player.setPosition(34,748);
            player.reset();
            setPlayer(player);
        }
    }

    //SETTER METHODS
    public void setPlayer(Player p) {
        player = p;
        if(on){
            player.setPosition((int) spawnX, (int) spawnY);
            player.setReference(this);
            for (int i = 0; i < followers.size(); i++) {
                player.getFollowers().add(followers.get(i));
                followers.get(i).setHolder(player);
            }
        }
    }

    public void setXOffset(double param) {
        xOffset = param;
    }

    //GETTER METHODS
    public double getXOffset() {
        return xOffset;
    }

    public double getYOffset() {return yOffset;}
    public Color getColor(){return color;}
    public ArrayList<Block> getBlocks() {return blocks;}
    public ArrayList<Follower> getFollowers() {return followers;}
    public ArrayList<Portal> getPortals(){return portals;}
    public ArrayList<Spikes> getSpikes(){return spikes;}
    public ArrayList<Lever> getLevers(){return levers;}
    public ArrayList<Bridge> getBridges(){return bridges;}
    public ArrayList<Gunner> getGunners(){return gunners;}
    public Goal getGoal(){return goal;}

    @Override
    public void update() {
        player.update();
        if(player.getYpos()>800)
        {
            player.die(this);
            gsm.reset();
            return;
        }
            //for(Block b: blocks){
        //    b.update();
        //}
        updatePositions();
        for (Follower f : followers) {
            f.update();
        }
        for (Portal p : portals) {
            p.update();
        }
        for(int i=0;i<spikes.size();i++)
            spikes.get(i).update();
        //updatePositions();
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).checkImage();
        }
    }

    public void typed(int c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
