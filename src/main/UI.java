package main;

import entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font pixel;
    Font font, font1, font2, font3, font3a, font4, font4a, font5, font6, font7;
    public String currentDialogue = "";
    public int commandNum = 0;
    int subState = 0;
    public int playerSlotCol = 0,playerSlotRow = 0;
    public int npcSlotCol = 0, npcSlotRow = 0;
    public int collectionSlotCol = 0,collectionSlotRow = 0;
    public int inventorySlotCol =0, inventorySlotRow = 0;
    BufferedImage image,fishImage,fishFrame;
    final BufferedImage tittle, humanImg, dinoImg, humanUnselect, dinoUnselect, coin;
    public int commonFish = 0,uncommonFish = 0,rareFish = 0, legendaryFish = 0, total = 0;
    public String fishName = "", fishPrice = "", fishRarity = " ",desFishing  = " ",desCollections= " ";
    public Entity npc;

    private int counter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            pixel = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

        //SET UP COIN IMAGE
        coin = setup("object/coin_bronze", gp.tileSize, gp.tileSize);

        //GET TYPE OF CHARACTER
        humanImg = setup("player/human", 1251, 1641);
        dinoImg = setup("player/Dino", 1244, 1707);
        humanUnselect = setup("player/human_Unselect", 1231, 1652);
        dinoUnselect = setup("player/dino_Unselect", 1252, 1693);
        tittle = setup("background/tittle", gp.screenWidth, gp.screenHeight);

        //FONT
        font = pixel.deriveFont(Font.BOLD, 60f);
        font1 = pixel.deriveFont(Font.BOLD, 30f);
        font2 = pixel.deriveFont(Font.BOLD, 10f);
        font3 = pixel.deriveFont(Font.BOLD, 20f);
        font3a = pixel.deriveFont(Font.PLAIN, 20f);
        font4 = pixel.deriveFont(Font.BOLD, 25f);
        font4a = pixel.deriveFont(Font.PLAIN, 22f);
        font5 = pixel.deriveFont(Font.BOLD, 38f);
        font6 = pixel.deriveFont(Font.PLAIN, 18f);
        font7 = pixel.deriveFont(Font.BOLD, 15f);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(pixel);
        g2.setColor(Color.white);

        //TITTLE STATE
        if (gp.gameState == gp.tittleState) {
            drawTittleScreen();
        }
        //SELECT PLAYER STATE
        if (gp.gameState == gp.selectPlayerState) {
            drawSelectPlayerScreen();
        }
        //PLAY STATE
        else if (gp.gameState == gp.playState) {
        }
        //DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        //AUTO DISPLAY STATE
        else if (gp.gameState == gp.autoDisplayState) {
            drawDialogueInteract();
        }
        //COLLECTION STATE
        else if (gp.gameState == gp.collectionState) {
            drawCollectionScreen();
        }
        //INVENTORY STATE
        else if (gp.gameState == gp.inventoryState) {
            drawInventoryScreen();
        }

        //TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }

    }

    public void drawTittleScreen() {
        //DRAW BACKGROUND
        g2.drawImage(tittle, 0, 0, null);

        //TITTLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        g2.setColor(Color.white);
        String text = "Holly Fish";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //SHADOW TEXT
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);

        //MAIN COLOR TEXT
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3;
        //TEXT OUTLINE
        g2.setColor(new Color(0x10439F));
        g2.drawString(text, x - 2, y - 2);
        g2.drawString(text, x - 2, y + 2);
        g2.drawString(text, x + 2, y - 2);
        g2.drawString(text, x + 2, y + 2);
        g2.drawString(text, x - 2, y);
        g2.drawString(text, x + 2, y);
        g2.drawString(text, x, y - 2);
        g2.drawString(text, x, y + 2);
        //DRAW TEXT
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(new Color(0xF9F07A));
            g2.drawString(text, x, y);
            g2.drawString(">", x - gp.tileSize, y);
            g2.setColor(Color.white);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        //TEXT OUTLINE
        g2.setColor(new Color(0x10439F));
        g2.drawString(text, x - 2, y - 2);
        g2.drawString(text, x - 2, y + 2);
        g2.drawString(text, x + 2, y - 2);
        g2.drawString(text, x + 2, y + 2);
        g2.drawString(text, x - 2, y);
        g2.drawString(text, x + 2, y);
        g2.drawString(text, x, y - 2);
        g2.drawString(text, x, y + 2);
        //DRAW TEXT
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(new Color(0xF9F07A));
            g2.drawString(text, x, y);
            g2.drawString(">", x - gp.tileSize, y);
            g2.setColor(Color.white);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        //TEXT OUTLINE
        g2.setColor(new Color(0x10439F));
        g2.drawString(text, x - 2, y - 2);
        g2.drawString(text, x - 2, y + 2);
        g2.drawString(text, x + 2, y - 2);
        g2.drawString(text, x + 2, y + 2);
        g2.drawString(text, x - 2, y);
        g2.drawString(text, x + 2, y);
        g2.drawString(text, x, y - 2);
        g2.drawString(text, x, y + 2);
        //DRAW TEXT
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.setColor(new Color(0xF9F07A));
            g2.drawString(text, x, y);
            g2.drawString(">", x - gp.tileSize, y);
            g2.setColor(Color.white);
        }
    }

    public void drawSelectPlayerScreen() {
        //DRAW BACKGROUND
        g2.setColor(new Color(0x9c9c9c));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //MENU
        int x;
        int y = gp.tileSize * 2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));

        //HUMAN
        x = gp.tileSize * 3;
        y += gp.tileSize;
        image = humanUnselect;
        g2.drawImage(image, x - 50, y, 1244 / 4, 1707 / 4, null);

        x += gp.tileSize / 2 - 5;
        y += gp.tileSize * 7;
        String text = "HUMAN";
        g2.setColor(Color.black);
        g2.drawString(text, x, y - 7);

        if (commandNum == 1) {
            g2.setColor(new Color(0x7AB2B2));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            image = humanImg;
            g2.drawImage(image, gp.tileSize * 3 - 50, gp.tileSize * 3, 1244 / 4, 1707 / 4, null);
            g2.setColor(Color.black);
            g2.drawString(text, x, y - 7);
        }

        //DINO
        x += gp.tileSize * 10;
        y = gp.tileSize * 3;
        image = dinoUnselect;
        g2.drawImage(image, x - 50, y, 1244 / 4, 1707 / 4, null);
        //TEXT OUTLINE
        text = "DINO";
        x += gp.tileSize - 5;
        y += gp.tileSize * 7;
        //DRAW TEXT
        g2.setColor(Color.black);
        g2.drawString(text, x, y - 7);
        if (commandNum == 2) {
            g2.setColor(new Color(0xa2c26a));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //DRAW DINO
            image = dinoImg;
            g2.drawImage(image, x - gp.tileSize + 5 - 50, y - gp.tileSize * 7, 1244 / 4, 1707 / 4, null);

            g2.setColor(Color.black);
            g2.drawString(text, x, y - 7);

            //DRAW HUMAN
            image = humanUnselect;
            g2.drawImage(image, gp.tileSize * 3 - 50, gp.tileSize * 3, 1244 / 4, 1707 / 4, null);

            x = gp.tileSize * 3 + gp.tileSize / 2 - 5;
            y = gp.tileSize * 10;
            text = "HUMAN";
            g2.setColor(Color.black);
            g2.drawString(text, x, y - 7);
        }

        //TITTLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        g2.setColor(Color.white);
        text = "Character";
        x = getXforCenteredText(text);
        y = gp.tileSize * 2;

        //SHADOW TEXT
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 5, y + 5);

        //MAIN COLOR TEXT
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    public void drawCollectionScreen() {
        drawCollectionBackground();
        setFontAndColor(font, new Color(0x74342E));

        g2.drawString("COLLECTIONS", center("COLLECTIONS", gp.tileSize, gp.tileSize * 15 / 2), gp.tileSize * 13 / 4);
        drawCollectionItemImage_Border_Number();
        drawCursor();
        displayItemIsChosen();
        displayStatistic();
    }


    public void drawCollectionBackground() {
        int x = gp.tileSize ;
        int y = gp.tileSize * 3 / 2;
        int width = gp.tileSize * 15 / 2;
        int height = gp.tileSize * 19 / 2;
        Color cbg = new Color(0xF4CE98);
        Color cs = new Color(0x5e3622);
        drawSubWindow1(x, y, width, height, cbg, cs, 10, 30);
        drawSubWindow1(x * 3 / 2 + width, y, gp.tileSize * 21 / 2, gp.tileSize * 5, cbg, cs, 10, 30);
        drawSubWindow1(x * 3 / 2 + width, gp.tileSize * 7, gp.tileSize * 21 / 2, gp.tileSize * 4, cbg, cs, 10, 30);

    }

    public void drawCollectionItemImage_Border_Number() {
        int count = 0;
        int imageAndBorderX = gp.tileSize * 2;
        int imageAndBorderY = gp.tileSize * 4;
        int amountX = gp.tileSize * 45 / 16;
        int amountY = gp.tileSize * 39 / 8;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (count <= 14) {
                    //draw imageOfFish
                    gp.collectionM.setImage(gp.collectionM.collection[count]);
                    g2.drawImage(gp.collectionM.collection[count].fishFinalImage, imageAndBorderX, imageAndBorderY, gp.tileSize, gp.tileSize, null);

                    //draw border
                    g2.setColor(new Color(0xA26D48));
                    g2.setStroke(new BasicStroke(5));
                    g2.drawRoundRect(imageAndBorderX, imageAndBorderY, gp.tileSize + 1, gp.tileSize + 1, 15, 15);

                    //display amount
                    g2.setFont(font2);
                    g2.setColor(Color.BLACK);
                    g2.drawString(String.valueOf(gp.collectionM.collection[count].count), amountX, amountY);

                    imageAndBorderX += gp.tileSize * 3 / 2;
                    amountX += gp.tileSize * 3 / 2;
                    count++;
                }
            }
            imageAndBorderX = gp.tileSize * 2;
            imageAndBorderY += gp.tileSize * 3 / 2;
            amountX = gp.tileSize * 45 / 16;
            amountY += gp.tileSize * 3 / 2;
        }
    }

    public void drawCursor() {
        final int xStart = gp.tileSize * 2;
        final int yStart = gp.tileSize * 4;

        int cursorX = xStart + (gp.tileSize * 3 / 2 * collectionSlotCol);
        int cursorY = yStart + (gp.tileSize * 3 / 2 * collectionSlotRow);

        g2.setColor(new Color(0xD46352));
        g2.drawRoundRect(cursorX, cursorY, gp.tileSize + 1, gp.tileSize + 1, 15, 15);
    }

    public void displayItemIsChosen() {
        int choose = 4 * collectionSlotRow + collectionSlotCol;
        //display fish chosen image
        if (choose <= 14) {
            g2.drawImage(gp.collectionM.collection[choose].fishFinalImage, gp.tileSize * 81 / 8, gp.tileSize * 9 / 4, gp.tileSize * 5 / 2, gp.tileSize * 5 / 2, null);
            String name = gp.collectionM.collection[choose].name;
            String rarity = gp.collectionM.collection[choose].fishRarity;
            int count = gp.collectionM.collection[choose].count;
            setFontAndColor(font4, new Color(0x7B322E));
            int y = gp.tileSize * 11 / 2;
            if (gp.collectionM.collection[choose].caught == false) {
                g2.drawString("?", center("?", gp.tileSize * 81 / 8, gp.tileSize * 5 / 2), y);
                int x = gp.tileSize * 29 / 2;
                y = gp.tileSize * 3;
                g2.drawString("Rarity: ?", x, y);
                y += 40;
                g2.drawString("Count: " + gp.collectionM.collection[choose].count, x, y);
            } else {
                setFontAndColor(font5, new Color(0x7B342E));
                g2.drawString(name, center(name, gp.tileSize * 81 / 8, gp.tileSize * 5 / 2), y);
                y += gp.tileSize * 1 / 2;
                g2.setFont(font4);
                switch (rarity) {
                    case "COMMON":
                        g2.setColor(new Color(0x448713));
                        break;
                    case "UNCOMMON":
                        g2.setColor(new Color(0x0239BD));
                        break;
                    case "RARE":
                        g2.setColor(new Color(0x810081));
                        break;
                    case "LEGENDARY":
                        g2.setColor(new Color(0xFF7F3E));
                        break;
                }
                int x = gp.tileSize * 29 / 2;
                y = gp.tileSize * 3;
                g2.drawString("Rarity: " + rarity, x, y);
                y += 40;
                g2.setColor(new Color(0x7B342E));
                g2.drawString("Count: " + count, x, y);
                y += 40;

                desCollections = gp.collectionM.collection[choose].desCollections;
                setFontAndColor(font4a, new Color(0x7B342E));
                for (String line : desCollections.split("\n")) {
                    g2.drawString(line, gp.tileSize * 547 / 40, y);
                    y += 20;
                }
            }
        }
    }

    public void displayStatistic() {
        setFontAndColor(font5, new Color(0x7B342E));
        g2.drawString("STATISTICS", center("STATISTICS", gp.tileSize * 9, gp.tileSize * 21 / 2), gp.tileSize * 31 / 4);
        g2.setFont(font4);
        int x1 = gp.tileSize * 10;
        int x2 = gp.tileSize * 15;
        int y = gp.tileSize * 35 / 4;
        g2.drawString("Common Fish:", x1, y);
        g2.drawString("Uncommon Fish:", x2, y);
        g2.drawString(commonFish + "", x1 + gp.tileSize * 13 / 4, y);
        g2.drawString(uncommonFish + "", x2 + gp.tileSize * 13 / 4, y);
        y += 40;
        g2.drawString("Rare Fish:", x1, y);
        g2.drawString("Legendary Fish:", x2, y);
        g2.drawString(rareFish + "", x1 + gp.tileSize * 13 / 4, y);
        g2.drawString(legendaryFish + "", x2 + gp.tileSize * 13 / 4, y);
        y += 40;
        g2.drawString("Total:", x1, y);
        g2.drawString(total + "", x1 + gp.tileSize * 13 / 4, y);
    }
    public void setFontAndColor(Font f, Color c) {
        g2.setColor(c);
        g2.setFont(f);
    }

    public void drawInventoryScreen() {
        //DRAW BACKGROUND
        int x = gp.tileSize * 11 / 2;
        int y = gp.tileSize * 3 / 4;
        drawSubWindow1(x, y, gp.tileSize * 9, gp.tileSize * 11, new Color(0xF4CE98), new Color(0x5e3622), 7, 30);
        drawSubWindow1(x + gp.tileSize * 1 / 2, y + gp.tileSize * 3 / 4, gp.tileSize * 8, gp.tileSize * 3, new Color(0xF4CE98), new Color(0x5e3622), 3, 30);

        // DRAW INVENTORY TITLE
        drawSubWindow1(gp.tileSize * 15 / 2, 5 + gp.tileSize / 4, gp.tileSize * 5, gp.tileSize - 10, new Color(0xF4CE98), new Color(0x5e3622), 5, 30);
        setFontAndColor(font5, new Color(0x7B342E));
        g2.drawString("INVENTORY", center("INVENTORY", gp.tileSize * 11 / 2, gp.tileSize * 9), gp.tileSize);

        // DRAW INVENTORY
        x = gp.tileSize*49/8;
        y +=  gp.tileSize*4;
        for(int i = 0; i <gp.player.inventory.size();i++){
            g2.drawImage(gp.player.inventory.get(i).collection_image,x,y,gp.tileSize*5/4,gp.tileSize*5/4,null);
            drawSubWindow1(x,y,gp.tileSize*5/4,gp.tileSize*5/4,new Color(0,0,0,0),new Color(0x5e3622),3,5);
            x += gp.tileSize*13/8;
            if(i == 4|| i == 9 || i==14|| i ==19){
                x =gp.tileSize*49/8;
                y +=  gp.tileSize*13/8;
            }
        }

        //DRAW CURSOR
        final int xStart = gp.tileSize * 49/8;
        final int yStart = gp.tileSize * 19/4;

        int cursorX = xStart + (gp.tileSize * 13/8 * inventorySlotCol);
        int cursorY = yStart + (gp.tileSize * 13/8 * inventorySlotRow);
        drawSubWindow1(cursorX,cursorY,gp.tileSize*5/4,gp.tileSize*5/4,new Color(0,0,0,0),new Color(0xD46352),3,5);

        //DISPLAY INFORMATION
        int choose = inventorySlotCol + 5*inventorySlotRow;
        x = gp.tileSize*13/2;
        y = gp.tileSize*2;
        if (choose < gp.player.inventory.size() ) {
            g2.drawImage(gp.player.inventory.get(choose).collection_image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            setFontAndColor(font4,new Color(0x7B342E));
            x += gp.tileSize*3;
            y += 40;
            g2.drawString("Name: "+gp.player.inventory.get(choose).name,x,y);
            y+= 30;
            g2.drawString("Price: "+gp.player.inventory.get(choose).price,x,y);
            y+= 30;
            g2.drawString("Current quantity: "+gp.player.inventory.get(choose).tradeCount,x,y);

        }
    }

    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 3;

        drawSubWindow1(x, y, width, height,new Color(0xF4CE98), new Color(0x5e3622),10,30);

        //TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawDialogueInteract() {
        double worldX, worldY;
        double screenX, screenY;
        int imageWidth, imageHeight;
        if (gp.player.interactEntity.get(gp.player.interactEntity_Index).name.equals("old man")) {
            worldX = gp.tileSize * 19.7;
            worldY = gp.tileSize * 8;
            imageWidth = gp.tileSize / 2;
            imageHeight = gp.tileSize / 2;
            //Coordinate for the screen
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;

            image = setup("Dialog/dialog", 28, 29);
        } else {
            worldX = gp.tileSize * 16.8;
            worldY = gp.tileSize * 1.5;
            imageWidth = gp.tileSize;
            imageHeight = gp.tileSize;
            //Coordinate for the screen
            screenX = worldX - gp.player.worldX + gp.player.screenX;
            screenY = worldY - gp.player.worldY + gp.player.screenY;

            image = setup("Dialog/CowDialog", 100, 85);
        }

        //STOP MOVING THE CAMERA AT EDGE (DIALOG CAN NOT MOVE IF AT EDGE)
        //TOP
        if (gp.player.screenX >= gp.player.worldX) {
            screenX = worldX;
        }
        //LEFT
        if (gp.player.screenY >= gp.player.worldY) {
            screenY = worldY;
        }
        //RIGHT
        double rightOffSet = gp.screenWidth - gp.player.screenX;
        if (rightOffSet >= gp.worldWidth - gp.player.worldX) {
            screenX = gp.screenWidth - (gp.worldWidth - worldX);
        }
        //BOTTOM
        double bottomOffSet = gp.screenHeight - gp.player.screenY;
        if (bottomOffSet >= gp.worldHeight - gp.player.worldY) {
            screenY = gp.screenHeight - (gp.worldHeight - worldY);
        }
        ////////////////////////

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, (int) screenX, (int) screenY, imageWidth, imageHeight, null);
        }
        //IF PLAYER AT THE EDGE
        else if (gp.player.screenX > gp.player.worldX ||
                gp.player.screenY > gp.player.worldY ||
                rightOffSet > gp.worldWidth - gp.player.worldX ||
                bottomOffSet > gp.worldHeight - gp.player.worldY) {
            g2.drawImage(image, (int) screenX, (int) screenY, imageWidth, imageHeight, null);
        }
    }

    //Trade UI
    public void drawTradeInventory(Entity entity, boolean cursor) {

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gp.player) {
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        //Frame
        drawSubWindow1(frameX, frameY, frameWidth, frameHeight,new Color(0xF4CE98), new Color(0x5e3622),10,30);

        //Slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //Draw Player's Items
        for (int i = 0; i < entity.inventory.size(); i++) {

            g2.drawImage(entity.inventory.get(i).tradeState_image, slotX, slotY, null);

            //Equip Cursor
            if(entity.inventory.get(i) == gp.player.currentFishingRod){
                g2.setColor(Color.RED);
                drawSubWindow1(slotX,slotY,gp.tileSize,gp.tileSize,new Color(0,0,0,0),new Color(0xD46352),3,10);
                // g2.drawRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10);
            }

            //Display amount
            if (entity == gp.player && entity.inventory.get(i).amount > 1) {

                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                //Shadow
                g2.setColor(new Color(60, 60, 60));
                g2.drawString(s, amountX + 10, amountY);
                //Number
                g2.setColor(new Color(0xF5e3622));
                g2.drawString(s, amountX + 7, amountY - 3);
            }

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //Cursor
        if (cursor) {
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            //Draw Cursor
            g2.setColor(new Color(0xF5e3622));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            //Description Frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;


            //Description Text
            g2.setFont(g2.getFont().deriveFont(28F));
            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if(itemIndex < entity.inventory.size()){
                drawSubWindow1(dFrameX, dFrameY, dFrameWidth, dFrameHeight,new Color(0xF4CE98), new Color(0x5e3622),10,30);
            }
        }
    }


    public void drawTradeScreen() {

        switch (subState) {
            case 0:
                trade_select();
                break;
            case 1:
                trade_buy();
                break;
            case 2:
                trade_sell();
                break;
        }
        gp.keyHandler.enterPressed = false;
    }

    public void trade_select() {
        drawDialogueScreen();

        //Draw
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow1(x, y, width, height,new Color(0xF4CE98), new Color(0x5e3622),10,30);


        //DrawText
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gp.keyHandler.enterPressed) {
                subState = 1;
            }
        }


        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gp.keyHandler.enterPressed) {
                subState = 2;
            }
        }


        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gp.keyHandler.enterPressed) {
                commandNum = 0;
                gp.gameState = gp.playState;
            }
        }

    }

    public void trade_buy() {
        //Player Inventory
        drawTradeInventory(gp.player, false);
        //NPC Inventory
        drawTradeInventory(npc, true);
        //Hint Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow1(x, y, width, height,new Color(0xF4CE98), new Color(0x5e3622),10,30);
        g2.drawString("[ESC] Back", x + 24, y + 60);
        //Player Coin Window
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow1(x, y, width, height,new Color(0xF4CE98), new Color(0x5e3622),10,30);
        g2.drawString("Your coin: " + gp.player.coin, x + 24, y + 60);
        //Price Window
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));

            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow1(x, y, width, height,new Color(0xF4CE98), new Color(0x5e3622),10,30);
            g2.drawImage(coin, x + 10, y + 10, 40, 40, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8);
            g2.drawString(text, x - 28, y + 45);
            //Description
            int textX = gp.tileSize*2 + 20;
            int textY = gp.tileSize*6 + gp.tileSize * 3/4;
            setFontAndColor(font4, new Color(0xF5e3622));
            g2.drawString(npc.inventory.get(itemIndex).name,textX,textY);
            textY += 30;
            setFontAndColor(font3a, new Color(0xF5e3622));
            for(String line: npc.inventory.get(itemIndex).desTrading.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 20;
            }

            //Buy an item
            if (gp.keyHandler.enterPressed) {
                if (npc.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You need more coin to buy that!";
                    drawDialogueScreen();
                } else {
                    if (gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        gp.player.coin -= npc.inventory.get(itemIndex).price;
                        npc.inventory.get(itemIndex).tradeCount ++;
                        if(npc.inventory.get(itemIndex).name == "Fishing Rod 1" || npc.inventory.get(itemIndex).name == "Fishing Rod 2" || npc.inventory.get(itemIndex).name == "Fishing Rod 3"){

                            //Change default fishingRod
                            gp.player.currentFishingRod = npc.inventory.get(itemIndex);

                            //remove item in npc inventory
                            npc.inventory.remove(itemIndex);

                            //remove item in player inventory
                            if(gp.player.currentFishingRod.name == "Fishing Rod 2"){
                                int previousItemIndex = gp.player.searchItemInInventory("Fishing Rod 1");
                                gp.player.inventory.remove(previousItemIndex);
                            }
                            if(gp.player.currentFishingRod.name == "Fishing Rod 3"){
                                int previousItemIndex = gp.player.searchItemInInventory("Fishing Rod 2");
                                gp.player.inventory.remove(previousItemIndex);
                            }
                        }
                    } else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "You cannot carry any more!";

                    }
                }
            }

        }
    }

    public void trade_sell() {
        //Player Inventory
        drawTradeInventory(gp.player, true);

        //Hint Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow1(x, y, width, height,new Color(0xF4CE98), new Color(0x5e3622),10,30);
        g2.drawString("[ESC] Back", x + 24, y + 60);
        //Player Coin Window
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 6;
        height = gp.tileSize * 2;
        drawSubWindow1(x, y, width, height,new Color(0xF4CE98), new Color(0x5e3622),10,30);
        g2.drawString("Your coin: " + gp.player.coin, x + 24, y + 60);
        //Price Window
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 38F));

            x = (int) (gp.tileSize * 15.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow1(x, y, width, height,new Color(0xF4CE98), new Color(0x5e3622),10,30);
            g2.drawImage(coin, x + 10, y + 10, 40, 40, null);

            int price = gp.player.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 18);
            g2.drawString(text, x - 28, y + 45);
            //Description
            int textX = gp.tileSize*12 + 20;
            int textY = gp.tileSize*6 + gp.tileSize * 3/4;
            setFontAndColor(font4,new Color(0xF5e3622));
            g2.drawString(gp.player.inventory.get(itemIndex).name,gp.tileSize*12 + 20,textY);
            textY += 30;
            setFontAndColor(font3a,new Color(0xF5e3622));
            for(String line: gp.player.inventory.get(itemIndex).desTrading.split("\n")){

                g2.drawString(line, textX, textY);
                textY += 20;
            }

            //Sell an item
            if (gp.keyHandler.enterPressed) {

                if (gp.player.inventory.get(itemIndex) == gp.player.currentFishingRod) {
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You cannot sell an equipped item!";
                } else {
                    if (gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    } else {
                        gp.player.inventory.get(itemIndex).tradeCount --;
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += price;
                }

            }
        }
    }

    public void drawGameOverScreen(){

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        //Shadow
        g2.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //Restart
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Restart";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">", x-40,y);
        }

        //Back to titleScreen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-40, y);
        }
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
    public int center(String s, int imageX, int imageWidth) {
        int textWidth = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        return imageX + (imageWidth - textWidth) / 2;
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    public void drawSubWindow1(int x, int y, int width, int height, Color cbg, Color cs, int strokeSize, int arc) {
        g2.setColor(cbg);
        g2.fillRoundRect(x, y, width, height, arc, arc);
        g2.setColor(cs);
        g2.setStroke(new BasicStroke(strokeSize));
        g2.drawRoundRect(x, y, width, height, arc, arc);
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