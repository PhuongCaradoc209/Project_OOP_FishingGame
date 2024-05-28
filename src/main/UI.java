package main;

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
    public int commandNum = 0;
    public int collectionSlotCol = 0,collectionSlotRow = 0;
    BufferedImage image,fishImage,fishFrame;
    final BufferedImage tittle, humanImg, dinoImg, humanUnselect, dinoUnselect, coin;
    public int commonFish = 0,uncommonFish = 0,rareFish = 0, legendaryFish = 0, total = 0;
    public String fishName = "", fishPrice = "", fishRarity = " ",desFishing  = " ",desCollections= " ";

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
        //COLLECTION STATE
        else if (gp.gameState == gp.collectionState) {
            drawCollectionScreen();
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

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
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

    public void drawSubWindow1(int x, int y, int width, int height, Color cbg, Color cs, int strokeSize, int arc) {
        g2.setColor(cbg);
        g2.fillRoundRect(x, y, width, height, arc, arc);
        g2.setColor(cs);
        g2.setStroke(new BasicStroke(strokeSize));
        g2.drawRoundRect(x, y, width, height, arc, arc);
    }
    public int center(String s, int imageX, int imageWidth) {
        int textWidth = (int) g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        return imageX + (imageWidth - textWidth) / 2;
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