import java.util.ArrayList;

public class Hall {

    private Film film;
    private String name;
    private int pricePerSeat;
    private int row;
    private int column;
    ArrayList<Seat> seats;


    public Hall(Film film, String name, int pricePerSeat, int row, int column) {
        this.film = film;
        this.name = name;
        this.pricePerSeat = pricePerSeat;
        this.row = row;
        this.column = column;
        seats = new ArrayList<>();
        Data.FindFilm(film.getName()).halls.add(this);
    }




    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(int pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


}
