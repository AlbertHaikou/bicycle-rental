package by.haikou.bicycle_rental.entity;

public class Parking {

    private int parkingId;
    private String street;

    public Parking() {
        super();
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Parking{" + "parkingId=" + parkingId + ", street=" + street + '}';
    }

}
