package Snake.GUI;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScoreHandler {
    public static void writeScore(String playerName, int score){
        try(FileWriter writer = new FileWriter("tablescore.txt", true))
        {
            writer.write(playerName+" "+score);
            writer.append("\n");
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    public static int readScore(){

        List<String> array = null;
        try {
            array = Files.readAllLines(Paths.get("tablescore.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int hightScore = 0;
        for (String line : array) {
            int score = Integer.parseInt(line.split(" ")[1]);
            if (score > hightScore)
                hightScore = score;
        }
        return hightScore;
    }
}
