package by.haikou.bicycle_rental.entity;

public class Parking {

    private Integer parkingId;
    private String street;

    public Parking() {
        super();
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
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
