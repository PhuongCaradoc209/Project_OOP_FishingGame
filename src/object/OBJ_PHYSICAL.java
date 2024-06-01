package object;

import main.GamePanel;
import entity.Entity;

public class OBJ_PHYSICAL extends Entity {
    public OBJ_PHYSICAL(GamePanel gp) {
        super(gp);
        name = "Physical";

        image = setup("object/0", gp.tileSize, gp.tileSize);
        image2 = setup("object/0.5", gp.tileSize, gp.tileSize);
        image3 = setup("object/1", gp.tileSize, gp.tileSize);
        image4 = setup("object/1.5", gp.tileSize, gp.tileSize);
        image5 = setup("object/2", gp.tileSize, gp.tileSize);

        collision = false;
    }
}