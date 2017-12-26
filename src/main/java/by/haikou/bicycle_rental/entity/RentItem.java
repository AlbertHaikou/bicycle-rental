package by.haikou.bicycle_rental.entity;

import java.util.Date;

public class RentItem extends AbstractEntity {

    private int id;
    private int bikeId;
    private int userId;
    private Date date;
    private Boolean status;

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "RentItem{" + "id=" + id + ", bikeId=" + bikeId + ", userId="
                + userId + ", date=" + date + ", status=" + status + '}';
    }

}
