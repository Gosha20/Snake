package Snake.Model;
public class Buff {

    public String name;
    public int countScore;
    public int timeLive;
    public int x;
    public int y;

    public Buff(String name, int countScore, int timeLive){
        this.name = name;
        this.countScore = countScore;
        this.timeLive = timeLive;
        x=0;
        y=0;
    }
}
