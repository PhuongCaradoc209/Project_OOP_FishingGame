package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 3;
    public int tileSize = originalTileSize * scale + 10; //58x58 (moej con dp)
    private final int maxScreenCol = 20;
    private final int maxScreenRow = 12;
    public int screenWidth = maxScreenCol * tileSize;//1160 px
    public int screenHeight = maxScreenRow * tileSize;//696px

    //WORLD SETTINGS
    public final int maxMap = 2;
    public int currentMap = 0;
    public final int maxWorldCol = 25;
    public final int maxWorldRow = 25;
    public final int worldWidth = maxWorldCol * tileSize;//2400
    public final int worldHeight = maxWorldRow * tileSize;//2400

    //GAME STATE
    public int gameState;
    public final int tittleState = 0;
    //PLAYER STATE
    public final int playState = 1;
    public final int autoDisplayState = 3;
    public final int dialogueState = 4;
    public final int notificationState = 5;
    public final int optionState = 6;
    public final int afterFishingState = 7;
    public final int collectionState = 8;
    public final int fishingState = 9;
    public final int selectPlayerState = 10;
    public final int tradeState = 11;
    public final int transitionState = 12;
    public final int fishTankState = 13;
    public final int inventoryState = 14;

    // FPS: Frame per second
    int FPS = 60;

    //SYSTEM
    public TileManager tileMgr = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound soundEffect = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //CHECK COLLISION
    public CollisionChecker cChecker = new CollisionChecker(this);


    // ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);
    public ArrayList<Entity>[] obj = new ArrayList[maxMap];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set size of this class (JPanel)
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // if set to true, all the drawing from this component will be done in an offscreen painting buffer
        this.setFocusable(true); // with this, this GamePanel can be "focused" to reveive key input
    }

    public void setupGame() {

    }

    public void startGameThread() {
        gameThread = new Thread(this); // "this" means this class (GamePanel)
        // we're passing Gamepanel class to this Thread's constructor
        gameThread.start();
    }

    @Override
    // public void run() {
        // double drawInterval = 1000000000/FPS; // draw the screen every 0.016666 seconds (1000000000 nanosecond = 1 second)
        // double nextDrawTime = System.nanoTime() + drawInterval; // System.nanoTime returns the current system time

        // while(gameThread != null) {
        //     //long currentTime = System.nanoTime();
        //     //System.out.println("Current time: " + currentTime); 


        //     // 1 UPDATE: update information such as character position
        //     update();

        //     // 2 DRAW: draw the screen with the updated information
        //     repaint();

        //     try {
        //         double remainingTime = nextDrawTime - System.nanoTime();
        //         remainingTime = remainingTime/1000000; // convert from nano to milliseconds

        //         if(remainingTime < 0) {
        //             remainingTime = 0;
        //         }

        //         Thread.sleep((long) remainingTime);

        //         nextDrawTime += drawInterval;
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }
    // }
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); // (currentTime - lastTime) = past time, in every loop, add past time to this timer

            lastTime = currentTime;

            if (delta >= 1) {
                // 1 = drawInterval
                update();
                repaint();
                delta --;
                drawCount++;
            }
            
            if (timer >= 1000000000) {
                // timer reachs 1 second
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // TILE
        tileMgr.draw(g2); // this line must be before "player.draw()"

//        // OBJECT
//        for (int i =  0; i < obj.length; i++) {
//            if (obj[i] != null) {
//                obj[i].draw(g2, this);
//            }
//        }

        // PLAYER
        player.draw(g2);

        g2.dispose(); // dispose of this graphics context and release any system resources that it is using
    }
}
