package main;

import java.io.*;

public class Config {
    GamePanel gp;
    private String s;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Config.txt"));
            //FULL SCREEN
            if (gp.fullScreenOn == true){
                bw.write("On");
            }else {
                bw.write("Off");
            }
            bw.newLine();

            //MUSIC VOLUME
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE VOLUME
            bw.write(String.valueOf(gp.soundEffect.volumeScale));
            bw.newLine();

            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("Config.txt"));
            s = br.readLine();

            //FULL SCREEN
            if (s.equals("On")){
                gp.fullScreenOn = true;
            }
            if (s.equals("Off")){
                gp.fullScreenOn = false;
            }

            //MUSIC VOLUME
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            //SE VOLUME
            s = br.readLine();
            gp.soundEffect.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
