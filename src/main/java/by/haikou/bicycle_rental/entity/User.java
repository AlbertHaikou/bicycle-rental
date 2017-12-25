package by.haikou.bicycle_rental.entity;

import java.util.List;

public class User extends AbstractEntity {
    public static enum Role {
        ADMINISTRATOR("ADMINISTRATOR"), USER("USER"), MANAGER("MANAGER");

        private String value;

        private Role(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private String firstName;
    private String email;
    private Integer id;
    private String lastName;
    private String password;
    private Role role;
    private boolean banned;

    public User() {
        super();
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
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

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", email=" + email +
                ", id=" + id + ", lastName=" + lastName + ", password=" + password +
                ", role=" + role + '}';
    }

}
