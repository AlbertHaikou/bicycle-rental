package by.haikou.bicycle_rental.dao.mysql.db;

import by.haikou.bicycle_rental.entity.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public final class ResultSetConverter {

    private ResultSetConverter() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static User createUserEntity(ResultSet set) throws SQLException {
        User entity = new User();

        entity.setId(set.getInt("id"));
        entity.setFirstName(set.getString("first_name"));
        entity.setLastName(set.getString("last_name"));
        entity.setEmail(set.getString("email"));
        entity.setPassword(set.getString("password"));
        entity.setBanned(set.getBoolean("banned"));
        entity.setBalance(set.getBigDecimal("balance"));
        entity.setPhoneNumber(set.getString("phone_number"));

        return entity;
    }

    public static Bicycle createBikeEntity(ResultSet set) throws SQLException {
        Bicycle entity = new Bicycle();

        entity.setBicycleId(set.getInt("id"));
        entity.setType(set.getString("type"));
        entity.setModel(set.getString("model"));
        entity.setSize(set.getString("size"));
        entity.setIsAvailable(set.getBoolean("available"));
        entity.setParkingId(set.getInt("fk_Parking_id"));
        entity.setPrice(set.getBigDecimal("price"));

        return entity;
    }

    public static Parking createParkingEntity(ResultSet set) throws SQLException {
        Parking entity = new Parking();

        entity.setParkingId(set.getInt("id"));
        entity.setStreet(set.getString("street"));

        return entity;
    }

    public static RentItem createRentItemEntity(ResultSet set) throws SQLException {

        Integer rentItem_id = set.getInt("id");
        Integer bikes_id = set.getInt("bicycle_id");
        Integer users_id = set.getInt("user_id");
        Integer parking_from_id = set.getInt("parking_from_id");
        Date startDate = set.getTimestamp("start_date");
        BigDecimal price = set.getBigDecimal("price");
        Date endDate = set.getTimestamp("end_date");
        BigDecimal totalPrice = set.getBigDecimal("total_price");


        RentItem entity = new RentItem();
        entity.setId(rentItem_id);
        entity.setBikeId(bikes_id);
        entity.setUserId(users_id);
        entity.setFromDate(startDate);
        entity.setParkingFromId(parking_from_id);
        entity.setPrice(price);

        if (null != endDate) {
            entity.setToDate(endDate);
        }
        if (null != totalPrice) {
            entity.setTotalPrice(totalPrice);
        }
        return entity;

    }

    public static RepairItemEntity createRepairItemEntity(ResultSet set) throws SQLException {

        Integer repairItem_id = set.getInt("id");
        Integer bikes_id = set.getInt("fk_bikes_id");
        String description = set.getString("description");
        Boolean status = set.getBoolean("status");

        RepairItemEntity entity = new RepairItemEntity();

        entity.setItemId(repairItem_id);
        entity.setBikeId(bikes_id);
        entity.setDescription(description);
        entity.setStatus(status);
        return entity;

    }

}
