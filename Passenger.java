public class Passenger {
    private final String name;
    private final int age;
    private final String gender;
    private final String seatPreference;

    public Passenger(String name, int age, String gender, String seatPreference) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.seatPreference = seatPreference;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getSeatPreference() {
        return seatPreference;
    }

    @Override
    public String toString() {
        return name + " | Age: " + age + " | Gender: " + gender
                + " | Seat: " + seatPreference;
    }
}