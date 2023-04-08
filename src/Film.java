import java.util.ArrayList;

public class Film {
    private String name;
    private String path;
    private int duration;
    ArrayList<Hall> halls;

    Film(){}
    public Film(String name, String path, int duration) {
        this.name = name;
        this.path = path;
        this.duration = duration;
        halls = new ArrayList<>();
    }

    Hall FindHall(String name){
        Hall hall = null;
        for(Hall i:halls){
            if(i.getName().equals(name)){
                hall = i;
            }
        }
        return hall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


}
