public class Seat {

    private Film film;
    private Hall hall;
    private int rowOfSeat;
    private int columnOfSeat;
    private User owner;
    private int price;

    public Seat(Film film, Hall hall, int rowOfSeat, int columnOfSeat, User owner, int price) {
        this.film = film;
        this.hall = hall;
        this.rowOfSeat = rowOfSeat;
        this.columnOfSeat = columnOfSeat;
        this.owner = owner;
        this.price = price;
        Data.FindHall(hall.getName()).seats.add(this);
    }


    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getRowOfSeat() {
        return rowOfSeat;
    }

    public void setRowOfSeat(int rowOfSeat) {
        this.rowOfSeat = rowOfSeat;
    }

    public int getColumnOfSeat() {
        return columnOfSeat;
    }

    public void setColumnOfSeat(int columnOfSeat) {
        this.columnOfSeat = columnOfSeat;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
