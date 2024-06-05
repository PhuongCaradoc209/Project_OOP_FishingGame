package object;

import main.GamePanel;
import entity.Entity;

import java.util.Random;

public class Fish_Redarowna extends Entity {
    public Fish_Redarowna(GamePanel gp){
        super(gp);
        fishRarity = "UNCOMMON";
        name = "Red Arowana";
        price = 5;
        fishStar = 2;

        getImage(gp);
        size = gp.tileSize * 2 + gp.tileSize/2;
        speed = 1;

        count = 0;
        fishFinalImage = setup("Item/Qm",gp.tileSize,gp.tileSize);
        fishFrame = setup("Fishingframe/Uncommon",gp.tileSize*18,gp.tileSize*15);
        desFishing="A type of freshwater fish known\nfor its vibrant red coloration and\nsleek, elongated body";
        desCollections="A type of freshwater fish known for\nits vibrant red coloration and sleek,\nelongated body";
        desTrading="A type of freshwater fish known for its\nvibrant red coloration and sleek, elongated\nbody";

    }


    public void getImage(GamePanel gp) {
        //Image for Collections
        collection_image = setup("Fish/Redarowna_2",gp.tileSize*5/2,gp.tileSize*5/2);
        //Image for tradeState
        tradeState_image = setup("Fish/Redarowna_2",gp.tileSize,gp.tileSize);

       //Image for FishTank
       diagonal_down_left1 = setup("Animal/Fish_Redarowna_left", 100, 100);
       diagonal_down_left2 = setup("Animal/Fish_Redarowna_left_2", 100, 100);
       diagonal_down_right1 = setup("Animal/Fish_Redarowna_right", 100, 100);
       diagonal_down_right2 = setup("Animal/Fish_Redarowna_right_2", 100, 100);

       left1 = setup("Animal/Fish_Redarowna_left", 100, 100);
       left2 = setup("Animal/Fish_Redarowna_left_2", 100, 100);
       right1 = setup("Animal/Fish_Redarowna_right", 100, 100);
       right2 = setup("Animal/Fish_Redarowna_right_2", 100, 100);

       diagonal_up_left1 = setup("Animal/Fish_Redarowna_left", 100, 100);
       diagonal_up_left2 = setup("Animal/Fish_Redarowna_left_2", 100, 100);
       diagonal_up_right1 = setup("Animal/Fish_Redarowna_right", 100, 100);
       diagonal_up_right2 = setup("Animal/Fish_Redarowna_right_2", 100, 100);
   }

   public void setAction() {
       actionLookCounter++;
       if (actionLookCounter == 120) {
           Random random = new Random();
           int i = random.nextInt(150) + 1;
           if (i <= 25) {
               direction = "diagonalDownLeft";
           } else if (i <= 50) {
               direction = "diagonalDownRight";
           } else if (i <= 75) {
               direction = "right";
           } else if (i <= 100) {
               direction = "left";
           } else if (i <= 125) {
               direction = "diagonalUpLeft";
           } else {
               direction = "diagonalUpRight";
           }
           actionLookCounter = 0;
       }
     }
}
