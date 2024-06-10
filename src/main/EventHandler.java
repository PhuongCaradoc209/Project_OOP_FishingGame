package main;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    private int count;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldRow][gp.maxWorldCol];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && row < gp.maxWorldRow && col < gp.maxWorldCol) {
            eventRect[map][row][col] = new EventRect();
            eventRect[map][row][col].x = 23;
            eventRect[map][row][col].y = 23;
            eventRect[map][row][col].width = 2;
            eventRect[map][row][col].height = 2;
            eventRect[map][row][col].eventRectDefaultX = eventRect[map][row][col].x;
            eventRect[map][row][col].eventRectDefaultY = eventRect[map][row][col].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent(int level_Rod) {
        if (gp.keyHandler.AnnouceCompleteAnimation) {
            count++;
            if (count == 1) {
                if (gp.player.inventory.size() < gp.player.maxInventorySize) {
                    if (gp.player.physical >= level_Rod) {
                        expendPhysical(level_Rod);
                        gp.ui.range_Y = gp.ui.random.nextInt(
                                gp.ui.bar_Y + 7 * gp.tileSize - gp.ui.heightOfRange - 20 - gp.ui.bar_Y - 10 + 1)
                                + gp.ui.bar_Y + 15;
                        gp.ui.speedOfRange = -gp.ui.speedOfRange;
                        gp.gameState = gp.fishingState;
                    } else {
                        // Out Of Energy
                        outOfEnergy(gp.notificationState);

                        // GameOver
                        if (gp.player.coin < 5 || gp.player.inventory.size() == 0) {
                            gameOver(gp.gameOverState);
                        }
                    }
                } else {
                    fullOfInventory(gp.notificationState);
                }
            }
        }
        if (!gp.keyHandler.AnnouceCompleteAnimation) {
            count = 0;
        }
        // if (hit(20, 12, "Up")){
        //
        // }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = (int) (gp.player.worldX + gp.player.solidArea.x);
            gp.player.solidArea.y = (int) (gp.player.worldY + gp.player.solidArea.y);

            eventRect[map][row][col].x = row * gp.tileSize + eventRect[map][row][col].x;
            eventRect[map][row][col].y = col * gp.tileSize + eventRect[map][row][col].y;

            if (gp.player.solidArea.intersects(eventRect[map][row][col])) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][row][col].x = eventRect[map][row][col].eventRectDefaultX;
            eventRect[map][row][col].y = eventRect[map][row][col].eventRectDefaultY;
        }

        return hit;
    }

    public void expendPhysical(int level_Rod) {
        gp.player.physical -= level_Rod;
    }

    public void addPhysical(int amount) {
        if (gp.player.physical <= gp.player.maxPhysical - amount) {
            gp.player.physical += amount;
        } else {
            gp.player.physical = gp.player.maxPhysical;
        }
    }

    public void outOfEnergy(int game_State) {
        gp.gameState = game_State;
        gp.ui.currentTittle = "OOPS!";
        gp.ui.currentNotification = "No more energy";
    }

    public void fullOfInventory(int game_State) {
        gp.gameState = game_State;
        gp.ui.currentTittle = "OOPS!";
        gp.ui.currentNotification = "You cannot carry any more!";
    }

    public void gameOver(int game_State) {
        gp.gameState = game_State;
    }
}