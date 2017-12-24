package by.haikou.bicycle_rental.entity;

public class ParkingEntity extends AbstractEntity {

    private static final long serialVersionUID = 6297385302078230511L;
    private Integer parkingId;
    private String street;

    public ParkingEntity() {
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
        return "ParkingEntity{" + "parkingId=" + parkingId + ", street=" + street + '}';
    }

}
