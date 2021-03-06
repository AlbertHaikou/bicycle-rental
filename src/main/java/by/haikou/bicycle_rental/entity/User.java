package by.haikou.bicycle_rental.entity;

import java.math.BigDecimal;

public class User {
    private String email;
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private boolean banned;
    private BigDecimal balance;
    private String phoneNumber;

    public User() {
        super();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean getBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", email=" + email +
                ", id=" + id + ", lastName=" + lastName + ", password=" + password +
                ", role=" + role + '}';
    }

    public static enum Role {
        ADMINISTRATOR, USER, MANAGER;

        public String getValue() {
            return this.name();
        }
    }

}
