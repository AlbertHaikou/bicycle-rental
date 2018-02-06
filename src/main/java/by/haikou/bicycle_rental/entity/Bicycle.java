package by.haikou.bicycle_rental.entity;

import java.math.BigDecimal;

public class Bicycle {

    private int bicycleId;
    private String type;
    private String model;
    private String size;
    private BigDecimal price;
    private boolean isAvailable;
    private int parkingId;

    public Bicycle() {
        super();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(int bikeId) {
        this.bicycleId = bikeId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    @Override
    public String toString() {
        return "Bicycle{" + "bikeId=" + bicycleId + ", type=" + type + ", model="
                + model + ", size=" + size + ", isAvailable=" + isAvailable
                + ", parkingId=" + parkingId + '}';
    }

}
