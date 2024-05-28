package entity;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity {
    KeyHandler key;
    TileManager tileM;

    public double screenX;
    public double screenY;

    public Player(GamePanel gp, KeyHandler key, TileManager tileM) {
        super(gp);
        this.key = key;
        this.tileM = tileM;
        size = gp.tileSize + 10;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 7;
        speed = (double) gp.worldWidth / 400;
        direction = "standDown";
    }

    public void getPlayerImage() {
        standUp = setup("player/standUp", 32, 32);
        standDown = setup("player/standDown", 32, 32);
        standRight = setup("player/right", 32, 32);
        standLeft = setup("player/left", 32, 32);
        up1 = setup("player/up_1", 32, 32);
        up2 = setup("player/up_2", 32, 32);
        down1 = setup("player/down_1", 32, 32);
        down2 = setup("player/down_2", 32, 32);
        left1 = setup("player/left_1", 32, 32);
        left2 = setup("player/left_2", 32, 32);
        right1 = setup("player/right_1", 32, 32);
        right2 = setup("player/right_2", 32, 32);
    }

    public void update() {
        if (key.downPressed || key.upPressed || key.leftPressed || key.rightPressed) {
            if (key.upPressed) {
                direction = "up";
            } else if (key.downPressed) {
                direction = "down";
            } else if (key.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }
        } else {
            if (Objects.equals(direction, "up")) {
                direction = "standUp";
            } else if (Objects.equals(direction, "down")) {
                direction = "standDown";
            } else if (Objects.equals(direction, "right")) {
                direction = "standRight";
            } else if (Objects.equals(direction, "left")) {
                direction = "standLeft";
            }
        }

        //UPDATE the solidArea due to zoom in and out
        solidArea.x = (10 * gp.tileSize) / 48;
        solidArea.y = (20 * gp.tileSize) / 48;
        solidArea.width = (30 * gp.tileSize) / 48;
        solidArea.height = (35 * gp.tileSize) / 48;


        //CHECK IF AT EDGE
        gp.cChecker.checkAtEdge(this);

        //IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            spriteNum = (spriteNum == 2) ? 1 : 2;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g) {
//        g.setColor(Color.white);
//        g.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
//                fishingRod.reset();
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
//                fishingRod.reset();
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
//                fishingRod.reset();
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
//                fishingRod.reset();
                break;
            case "standUp":
                image = standUp;
                break;
            case "standDown":
                image = standDown;
                break;
            case "standRight":
                image = standRight;
                break;
            case "standLeft":
                image = standLeft;
                break;
        }
//        ////////////////////////
//        if(fishingRod.getFrame() != null){
//            image = fishingRod.getFrame();
//            g.drawImage(image, (int) (x - ( this.direction=="standLeft" ? (size) : 0)), (int) y, null);
//        }
//        else
//            g.drawImage(image, (int) x, (int) y, size, size, null);

    }
}
