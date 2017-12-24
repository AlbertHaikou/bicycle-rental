package by.haikou.bicycle_rental.dao.mysql.db;

import by.haikou.bicycle_rental.entity.*;
import by.haikou.bicycle_rental.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public final class ResultSetConverter {

    private ResultSetConverter() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static User createUserEntity(ResultSet set) throws SQLException {
        Integer userId = set.getInt("id");
        String firstName = set.getString("firstName");
        String lastName = set.getString("lastName");
        String email = set.getString("email");
        String password = set.getString("password");
        User entity = new User();

        entity.setId(userId);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setEmail(email);
        entity.setPassword(password);
        return entity;
    }

    public static Bicycle createBikeEntity(ResultSet set) throws SQLException {
        Integer bikeId = set.getInt("id");
        String type = set.getString("type");
        String model = set.getString("model");
        String size = set.getString("size");
        Boolean available = set.getBoolean("available");
        Integer parkingId = set.getInt("fk_Parking_id");

        Bicycle entity = new Bicycle();

        entity.setBicycleId(bikeId);
        entity.setType(type);
        entity.setModel(model);
        entity.setSize(size);
        entity.setIsAvailable(available);
        entity.setParkingId(parkingId);

        return entity;
    }

    public static Parking createParkingEntity(ResultSet set) throws SQLException {
        Integer parkingId = set.getInt("id");
        String street = set.getString("street");

        Parking entity = new Parking();

        entity.setParkingId(parkingId);
        entity.setStreet(street);
        return entity;
    }

    public static RentItem createRentItemEntity(ResultSet set) throws SQLException {

        Integer rentItem_id = set.getInt("id");
        Integer bikes_id = set.getInt("fk_bikes_id");
        Integer users_id = set.getInt("fk_users_id");
        Date date = set.getTimestamp("date");
        Boolean status = set.getBoolean("status");

        RentItem entity = new RentItem();
        entity.setId(rentItem_id);
        entity.setBikeId(bikes_id);
        entity.setUserId(users_id);
        entity.setDate(date);
        entity.setStatus(status);
        return entity;

    }

    public static SupportItemEntity createSupportItemEntity(ResultSet set) throws SQLException {

        Integer supportItem_id = set.getInt("id");
        Integer bikes_id = set.getInt("fk_bikes_id");
        String description = set.getString("description");
        Boolean status = set.getBoolean("status");

        SupportItemEntity entity = new SupportItemEntity();

        entity.setId(supportItem_id);
        entity.setBikeId(bikes_id);
        entity.setDescription(description);
        entity.setStatus(status);
        return entity;

    }

}
