package by.haikou.bicycle_rental.util;

public enum Role {
    ADMIN(1, "admin"), SUPPORT(2, "support"), USER(3, "user"),;

    private int id;
    private String name;

    private Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }
}
