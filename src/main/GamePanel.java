package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    private final int originalTileSize = 16;
    private final int scale = 3;
    public int tileSize = originalTileSize * scale + 10; //58x58
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

    //FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    boolean fullScreenOn = false;

    //SYSTEM
    public TileManager tileMgr = new TileManager(this);
    public CollectionManagement collectionM = new CollectionManagement(this) ;
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //CHECK COLLISION
    public CollisionChecker cChecker = new CollisionChecker(this);
    //PLAYER
    public Player player = new Player(this, keyHandler, tileMgr);
    //OBJECT
    public ArrayList<Entity>[] obj = new ArrayList[maxMap];
    //ANIMAL
    public ArrayList<Entity>[] animal = new ArrayList[maxMap];

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

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setVisible(true);
        this.setFocusable(true);
    }

    public void setupGame() {
        //CREATE ARRAYLIST FOR ENTITY
        for (int i = 0; i < maxMap; i++) {
            animal[i] = new ArrayList<>();
        }
        //SET ON MAP
        aSetter.setAnimal(currentMap);

        gameState = tittleState;
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;

        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //display FPS
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) //as long as gameThread is existed
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawToTempScreen(); //draw to the buffered image
                drawToScreen(); //draw the buffered image to the screen
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState || gameState == autoDisplayState) {
            if (!music.isPlaying("Bird") && !music.isPlaying("Background")) {
                playMusic("Bird", 0);
                playMusic("Background", 2);
            }
            player.update();
            for (int i = 0; i < animal[0].size(); i++) {
                if (animal[0].get(i) != null) {
                    if (i < 4) {
                        animal[0].get(i).update(true);
                    } else animal[0].get(i).update(false);
                }
            }
        }
    }
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void drawToTempScreen() {
        //DEBUG
        long drawStart = 0;
        if (keyHandler.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        //TITTLE SCREEN
        if (gameState != tittleState && currentMap == 0) {
            //TILE
            tileMgr.draw(g2);

            player.draw(g2);

            for (Entity value : animal[currentMap]) {
                if (value != null) {
                    value.draw(g2);
                }
            }
        }

        //UI
        ui.draw(g2);

        if (keyHandler.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

    }

    public void playMusic(String soundName, int i) {
        if (!music.isPlaying(soundName)) {
            music.setField(i);
            music.loop(soundName);
        }
    }

    public void stopMusic(String soundName) {
        music.stop(soundName);
    }

    public void playSoundEffect(String soundName, int i) {
        soundEffect.setField(i);
        soundEffect.playSE(soundName);
    }
}
