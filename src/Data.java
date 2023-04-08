import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;

public class Data {
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Film> films = new ArrayList<>();
    static ArrayList<Seat> seats = new ArrayList<>();
    static ArrayList<Hall> halls = new ArrayList<>();
    static ObservableList<User> users2 = FXCollections.observableArrayList();

    static void createObjects() throws IOException {
        File file = new File("assets\\data\\backup.dat");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] linelist;
        while ((line = bufferedReader.readLine()) != null){
            linelist = line.split("\t");
            if(linelist[0].equals("user")){
                users.add(new User(linelist[1], linelist[2], Boolean.parseBoolean(linelist[3]), Boolean.parseBoolean(linelist[4]) ));
            }
            else if(linelist[0].equals("film")){
                films.add(new Film(linelist[1], linelist[2],Integer.parseInt(linelist[3])));
            }
            else if(linelist[0].equals("seat")){
                seats.add(new Seat(FindFilm(linelist[1]), FindHall(linelist[2]), Integer.parseInt(linelist[3]), Integer.parseInt(linelist[4]), FindUser(linelist[5]), Integer.parseInt(linelist[6])));
            }
            else if(linelist[0].equals("hall")){
                halls.add(new Hall(FindFilm(linelist[1]), linelist[2], Integer.parseInt(linelist[3]), Integer.parseInt(linelist[4]), Integer.parseInt(linelist[5])));
            }
        }
        bufferedReader.close();

    }

    static void updateData() throws IOException {
        File file = new File("assets\\data\\backup.dat");
        FileWriter fileWriter = new FileWriter(file,false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(User i:users)
            bufferedWriter.write("user\t" + i.getName() + "\t" + i.getPassword() + "\t" + i.isClubMember() + "\t" + i.isAdmin() + "\n");
        for(Film i:films)
            bufferedWriter.write("film\t" + i.getName() + "\t" + i.getPath() + "\t" + i.getDuration() + "\n");
        for(Hall j:halls)
            bufferedWriter.write("hall\t" + j.getFilm().getName() + "\t" + j.getName() + "\t" + j.getPricePerSeat() + "\t" + j.getRow() + "\t"+ j.getColumn() + "\n");
        for(Seat k: seats) {
            if (k.getOwner() == null)
                bufferedWriter.write("seat\t" + k.getFilm().getName() + "\t" + k.getHall().getName() + "\t" + k.getRowOfSeat() + "\t" + k.getColumnOfSeat() + "\t" + "null" + "\t" + k.getPrice() + "\n");
            else
                bufferedWriter.write("seat\t" + k.getFilm().getName() + "\t" + k.getHall().getName() + "\t" + k.getRowOfSeat() + "\t" + k.getColumnOfSeat() + "\t" + k.getOwner().getName() + "\t" + k.getPrice() + "\n");
        }
        bufferedWriter.close();
    }

    static Film FindFilm(String name){
        Film film = null;
        for(Film i:films){
            if(i.getName().equals(name)){
                film = i;
            }
        }
        return film;
    }

    static Hall FindHall(String name){
        Hall hall = null;
        for(Hall i:halls){
            if(i.getName().equals(name)){
                hall = i;
            }
        }
        return hall;
    }

    static User FindUser(String name){
        User user = null;
        for(User i:users){
            if(i.getName().equals(name)){
                user = i;
            }
        }
        return user;
    }
}
