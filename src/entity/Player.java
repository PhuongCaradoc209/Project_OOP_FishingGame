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
    private double x, y;
    private int objIndex;

    public Player(GamePanel gp, KeyHandler key, TileManager tileM) {
        super(gp);
        this.key = key;
        this.tileM = tileM;
        size = gp.tileSize + 10;

        screenX = (double) gp.screenWidth / 2 - ((double) gp.tileSize / 2); //set the player at then center of the screen
        screenY = (double) gp.screenHeight / 2 - ((double) gp.tileSize / 2);

        setDefaultValues();

        //AREA COLLISION
        solidArea = new Rectangle();
        solidArea.x = (8 * gp.tileSize) / 48;
        solidArea.y = (16 * gp.tileSize) / 48;
        solidArea.width = (32 * gp.tileSize) / 48;
        solidArea.height = (32 * gp.tileSize) / 48;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        direction = "down";
    }

    public void setPlayerImage(String playerType) {
        if (playerType.equals("Human")) {
            getPlayerImage_HumanVer();
        } else
            getPlayerImage_DinoVer();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 10;
        worldY = gp.tileSize * 7;
        speed = (double) gp.worldWidth / 400;
        direction = "standDown";
    }

    public void getPlayerImage_DinoVer() {
        standUp = setup("player/dino_up_1", 16, 16);
        standDown = setup("player/dino_down_1", 16, 16);
        standRight = setup("player/dino_down_1", 16, 16);
        standLeft = setup("player/dino_up_1", 16, 16);
        up1 = setup("player/dino_up_1", 16, 16);
        up2 = setup("player/dino_up_2", 16, 16);
        down1 = setup("player/dino_down_1", 16, 16);
        down2 = setup("player/dino_down_2", 16, 16);
        left1 = setup("player/dino_left_1", 16, 16);
        left2 = setup("player/dino_left_2", 16, 16);
        right1 = setup("player/dino_right_1", 16, 16);
        right2 = setup("player/dino_right_2", 16, 16);
    }

    public void getPlayerImage_HumanVer() {
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

        //CHECK TILE COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this, false);

        //CHECK OBJ COLLISION
        objIndex = gp.cChecker.checkObj(this, true);

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
        //STOP MOVING THE CAMERA AT EDGE (PLAYER CAN NOT MOVE IF AT EDGE)
        x = screenX;
        y = screenY;
        //TOP
        if (gp.player.screenX >= worldX) {
            x = worldX;
        }
        //LEFT
        if (gp.player.screenY >= worldY) {
            y = worldY;
        }
        //RIGHT
        double rightOffSet = gp.screenWidth - screenX;
        if (rightOffSet >= gp.worldWidth - worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        //BOTTOM
        double bottomOffSet = gp.screenHeight - screenY;
        if (bottomOffSet >= gp.worldHeight - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }
        g.drawImage(image, (int) x, (int) y, size, size, null);

//        ////////////////////////
//        if(fishingRod.getFrame() != null){
//            image = fishingRod.getFrame();
//            g.drawImage(image, (int) (x - ( this.direction=="standLeft" ? (size) : 0)), (int) y, null);
//        }
//        else
//            g.drawImage(image, (int) x, (int) y, size, size, null);

    }
}
