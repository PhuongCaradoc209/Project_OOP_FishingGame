package main;

import entity.Animal_Duck;

public class AssetSetter {
    GamePanel gp;
    private int i,mapNum;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
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
        }
    }
}
