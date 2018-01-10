package by.haikou.bicycle_rental.entity;

import java.util.Date;

public class RentItem extends AbstractEntity {

    private int id;
    private int bikeId;
    private int userId;
    private int parkingFromId;
    private int parkingToId;
    private Date date;
    private Boolean status;

    public void setId(int id) {
        this.id = id;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RentItem{" + "id=" + id + ", bikeId=" + bikeId + ", userId="
                + userId + ", date=" + date + ", status=" + status + '}';
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
