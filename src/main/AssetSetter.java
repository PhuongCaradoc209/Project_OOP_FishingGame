package main;

import entity.Animal_Cow;
import entity.Animal_Duck;
import entity.NPC_OldMan;
import object.*;

public class AssetSetter {
    GamePanel gp;
    private int i,mapNum;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        mapNum = 0;
        i = 0;

        gp.obj[mapNum].add(new OBJ_WALL_BOT(gp));
        gp.obj[mapNum].get(i).worldX = 19 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_BOT(gp));
        gp.obj[mapNum].get(i).worldX = 21 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_BOT(gp));
        gp.obj[mapNum].get(i).worldX = 22 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_LEFT(gp));
        gp.obj[mapNum].get(i).worldX = 18 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_LEFT(gp));
        gp.obj[mapNum].get(i).worldX = 18 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_LEFT(gp));
        gp.obj[mapNum].get(i).worldX = 18 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_LEFT(gp));
        gp.obj[mapNum].get(i).worldX = 18 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_RIGHT(gp));
        gp.obj[mapNum].get(i).worldX = 23 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_RIGHT(gp));
        gp.obj[mapNum].get(i).worldX = 23 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_RIGHT(gp));
        gp.obj[mapNum].get(i).worldX = 23 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_RIGHT(gp));
        gp.obj[mapNum].get(i).worldX = 23 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_TOP(gp));
        gp.obj[mapNum].get(i).worldX = 19 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_TOP(gp));
        gp.obj[mapNum].get(i).worldX = 20 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_TOP(gp));
        gp.obj[mapNum].get(i).worldX = 21 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum].add(new OBJ_WALL_TOP(gp));
        gp.obj[mapNum].get(i).worldX = 22 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 7 * gp.tileSize;
        i++;

        //Interior
        gp.obj[mapNum].add(new OBJ_SHELF(gp));
        gp.obj[mapNum].get(i).worldX = 22 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 9 * gp.tileSize;
        i++;
        gp.obj[mapNum].add(new OBJ_SHELF(gp));
        gp.obj[mapNum].get(i).worldX = 21 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 9 * gp.tileSize;
        i++;
        gp.obj[mapNum].add(new OBJ_SHELF(gp));
        gp.obj[mapNum].get(i).worldX = 20 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 9 * gp.tileSize;
        i++;
        gp.obj[mapNum].add(new OBJ_SHELF(gp));
        gp.obj[mapNum].get(i).worldX = 19 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 9 * gp.tileSize;
        i++;
        gp.obj[mapNum].add(new OBJ_SHELF_LEFT(gp));
        gp.obj[mapNum].get(i).worldX = 19 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 8 * gp.tileSize;
        i++;
        gp.obj[mapNum].add(new OBJ_SHELF_RIGHT(gp));
        gp.obj[mapNum].get(i).worldX = 22 * gp.tileSize;
        gp.obj[mapNum].get(i).worldY = 8 * gp.tileSize;
        i++;
        gp.obj[mapNum].add(new OBJ_SOFA_TOP(gp));
        gp.obj[mapNum].get(i).worldX = (23 * gp.tileSize) - 16;
        gp.obj[mapNum].get(i).worldY = (11 * gp.tileSize) - 30;
        i++;
        gp.obj[mapNum].add(new OBJ_SOFA_BOTTOM(gp));
        gp.obj[mapNum].get(i).worldX = (23 * gp.tileSize) - 16;
        gp.obj[mapNum].get(i).worldY = (12 * gp.tileSize) - 30;
        i++;
        gp.obj[mapNum].add(new OBJ_CHEST(gp));
        gp.obj[mapNum].get(i).worldX = (19 * gp.tileSize) + 33;
        gp.obj[mapNum].get(i).worldY = (7 * gp.tileSize) + 22;
        i++;
        gp.obj[mapNum].add(new OBJ_CHEST(gp));
        gp.obj[mapNum].get(i).worldX = (20 * gp.tileSize) + 30;
        gp.obj[mapNum].get(i).worldY = (7 * gp.tileSize) + 22;
        i++;
        gp.obj[mapNum].add(new OBJ_CHEST(gp));
        gp.obj[mapNum].get(i).worldX = (21 * gp.tileSize) + 27;
        gp.obj[mapNum].get(i).worldY = (7 * gp.tileSize) + 22;
        i++;
        gp.obj[mapNum].add(new OBJ_FLOWER(gp));
        gp.obj[mapNum].get(i).worldX = (21 * gp.tileSize) + 5;
        gp.obj[mapNum].get(i).worldY = (13 * gp.tileSize) - 15;
        i++;
        gp.obj[mapNum].add(new OBJ_FLOWER(gp));
        gp.obj[mapNum].get(i).worldX = (22 * gp.tileSize) - 5;
        gp.obj[mapNum].get(i).worldY = (13 * gp.tileSize) - 15;
        i++;
        gp.obj[mapNum].add(new OBJ_FLOWER(gp));
        gp.obj[mapNum].get(i).worldX = (23 * gp.tileSize) - 15;
        gp.obj[mapNum].get(i).worldY = (13 * gp.tileSize) - 15;
        i++;

    }

    public void setNPC() {
        mapNum = 0;

        gp.npc[mapNum].add(new NPC_OldMan(gp));
        gp.npc[mapNum].get(0).worldX = gp.tileSize * 20;
        gp.npc[mapNum].get(0).worldY = gp.tileSize * 8;
    }

    public void setAnimal(int mapNum) {
        //PLAYER STATE
        i = 0;
        if (mapNum == 0) {
            gp.animal[mapNum].add(new Animal_Duck(gp));
            gp.animal[mapNum].get(i).worldX = gp.tileSize * 5;
            gp.animal[mapNum].get(i).worldY = gp.tileSize * 3;
            i++;

            gp.animal[mapNum].add(new Animal_Duck(gp));
            gp.animal[mapNum].get(i).worldX = gp.tileSize * 2;
            gp.animal[mapNum].get(i).worldY = gp.tileSize * 9;
            i++;

            gp.animal[mapNum].add(new Animal_Duck(gp));
            gp.animal[mapNum].get(i).worldX = gp.tileSize * 4;
            gp.animal[mapNum].get(i).worldY = gp.tileSize * 10;
            i++;

            gp.animal[mapNum].add(new Animal_Duck(gp));
            gp.animal[mapNum].get(i).worldX = gp.tileSize * 5;
            gp.animal[mapNum].get(i).worldY = gp.tileSize * 20;
            i++;

            gp.animal[mapNum].add(new Animal_Cow(gp));
            gp.animal[mapNum].get(i).worldX = gp.tileSize * 17;
            gp.animal[mapNum].get(i).worldY = gp.tileSize * 2;
            i++;
        }
    }

}
