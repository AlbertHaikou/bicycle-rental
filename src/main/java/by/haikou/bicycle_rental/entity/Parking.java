package by.haikou.bicycle_rental.entity;

public class Parking extends AbstractEntity {

    private Integer parkingId;
    private String street;

    public Parking() {
        super();
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "Parking{" + "parkingId=" + parkingId + ", street=" + street + '}';
    }

}
