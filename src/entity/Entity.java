package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Entity {
    public GamePanel gp;
    protected int size;
    public int worldX;
    public int worldY;

    public double speed;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2,
            standUp, standDown, standRight, standLeft;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle();
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    //OBJ
    public int price;
    public String name;
    public boolean collision = false;

    //Fish
    public BufferedImage collection_image, tradeState_image;
    public int fishStar;
    public String fishRarity;
    public BufferedImage fishFrame;
    public int count;
    public int tradeCount;
    public boolean caught = false;
    public BufferedImage fishFinalImage;
    public String desCollections;
    public String desFishing;
    public String desTrading = "";

    public Entity(GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    }
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
