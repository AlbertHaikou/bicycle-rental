package by.haikou.bicycle_rental.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RentItem {

    private int id;
    private int bikeId;
    private int userId;
    private int parkingFromId;
    private int parkingToId;
    private Date fromDate;
    private Date toDate;
    private BigDecimal price;
    private boolean status;
    private BigDecimal totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RentItem{" + "id=" + id + ", bikeId=" + bikeId + ", userId="
                + userId + ", fromDate=" + fromDate + ", status=" + status + '}';
    }

    public int getParkingFromId() {
        return parkingFromId;
    }

    public void setParkingFromId(int parkingFromId) {
        this.parkingFromId = parkingFromId;
    }

    public int getParkingToId() {
        return parkingToId;
    }

    public void setParkingToId(int parkingToId) {
        this.parkingToId = parkingToId;
    }
}
