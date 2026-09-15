public class Train {
    private final int trainNumber;
    private final String trainName;
    private final String source;
    private final String destination;
    private final int totalSeats;
    private int availableSeats;
    private final double fare;

    public Train(int trainNumber, String trainName, String source,
                 String destination, int totalSeats, double fare) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.fare = fare;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getFare() {
        return fare;
    }

    public boolean reserveSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    public void releaseSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    @Override
    public String toString() {
        return trainNumber + " | " + trainName + " | " + source + " -> "
                + destination + " | Seats: " + availableSeats
                + " | Fare: Rs." + String.format("%.2f", fare);
    }
}