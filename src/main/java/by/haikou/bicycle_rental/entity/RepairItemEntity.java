package by.haikou.bicycle_rental.entity;

public class RepairItemEntity {

    private int itemId;
    private int bikeId;
    private String description;
    private Boolean status;

    public RepairItemEntity() {
        super();
    }

    public int getItemId() {
        return itemId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "RepairItemEntity{" + "itemId=" + itemId + ", bikeId=" + bikeId
                + ", description=" + description + ", status=" + status + '}';
    }

}
