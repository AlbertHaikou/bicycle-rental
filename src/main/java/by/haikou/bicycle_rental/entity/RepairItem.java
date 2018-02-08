package by.haikou.bicycle_rental.entity;

public class RepairItem {

    private int itemId;
    private int bikeId;
    private String description;
    private boolean status;

    public RepairItem() {
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

    public boolean getStatus() {
        return status;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "RepairItem{" + "itemId=" + itemId + ", bikeId=" + bikeId
                + ", description=" + description + ", status=" + status + '}';
    }

}
