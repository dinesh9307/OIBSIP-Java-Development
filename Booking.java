import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
    private final String pnr;
    private final String username;
    private final Train train;
    private final Passenger passenger;
    private final LocalDateTime bookingTime;
    private String status;

    public Booking(String pnr, String username, Train train, Passenger passenger) {
        this.pnr = pnr;
        this.username = username;
        this.train = train;
        this.passenger = passenger;
        this.bookingTime = LocalDateTime.now();
        this.status = "CONFIRMED";
    }

    public String getPnr() {
        return pnr;
    }

    public String getUsername() {
        return username;
    }

    public Train getTrain() {
        return train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getStatus() {
        return status;
    }

    public void cancel() {
        status = "CANCELLED";
    }

    public String getBookingTime() {
        return bookingTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public void display() {
        System.out.println("\n--------------- TICKET ---------------");
        System.out.println("PNR          : " + pnr);
        System.out.println("Passenger    : " + passenger.getName());
        System.out.println("Age/Gender   : " + passenger.getAge() + "/" + passenger.getGender());
        System.out.println("Seat Pref.   : " + passenger.getSeatPreference());
        System.out.println("Train        : " + train.getTrainName());
        System.out.println("Train No.    : " + train.getTrainNumber());
        System.out.println("Route        : " + train.getSource() + " -> " + train.getDestination());
        System.out.println("Fare         : Rs." + String.format("%.2f", train.getFare()));
        System.out.println("Booked At    : " + getBookingTime());
        System.out.println("Status       : " + status);
        System.out.println("--------------------------------------");
    }
}