package by.haikou.bicycle_rental.entity;

import by.haikou.bicycle_rental.util.Role;

import java.util.List;

public class UserEntity extends AbstractEntity {

    private static final long serialVersionUID = 6297385302078200511L;
    private String firstName;
    private String email;
    private Integer id;
    private String lastName;
    private String password;
    private List<Role> roles;
    private boolean banned;

    public UserEntity() {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
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
        return "UserEntity{" + "firstName=" + firstName + ", email=" + email +
                ", id=" + id + ", lastName=" + lastName + ", password=" + password +
                ", roles=" + roles + '}';
    }

}
