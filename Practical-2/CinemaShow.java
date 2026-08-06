public class CinemaShow {

    // Private data members
    private String title;
    private int seatsAvailable;
    private final int capacity;

    // Static variable
    private static int totalBooked = 0;

    // Constructor with title and capacity
    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    // Constructor with only title
    public CinemaShow(String title) {
        this(title, 100);
    }

    // Book seats
    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    // Cancel seats
    public void cancel(int n) {
        seatsAvailable += n;
        if (seatsAvailable > capacity) {
            seatsAvailable = capacity;
        }
    }

    // Getter for available seats
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    // Static getter for total booked seats
    public static int getTotalBooked() {
        return totalBooked;
    }

    // Main method
    public static void main(String[] args) {

        CinemaShow show = new CinemaShow("Avengers", 50);

        System.out.println("Book 20: " + show.book(20));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Book 25: " + show.book(25));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Book 10: " + show.book(10)); // Not enough seats
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        show.cancel(15);
        System.out.println("After Cancel 15 Seats: " + show.getSeatsAvailable());

        System.out.println("Book 10: " + show.book(10));
        System.out.println("Seats Available: " + show.getSeatsAvailable());

        System.out.println("Total Booked Seats: " + CinemaShow.getTotalBooked());
    }
}