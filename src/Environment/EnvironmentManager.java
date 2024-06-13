package Environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    GamePanel gp;
    private DayNight dayNight;
    public EnvironmentManager(GamePanel gp){
        this.gp = gp;
    }

    public void setUp(){
        dayNight = new DayNight(gp);
    }
    public void update(){
        dayNight.update();
    }
    public void draw(Graphics2D g2){
        dayNight.draw(g2);
    }

}
