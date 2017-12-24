package by.haikou.bicycle_rental.exception;

import by.haikou.bicycle_rental.entity.UserEntity;

public class UserException extends Exception {
    private UserEntity user;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, UserEntity user) {
        this(message);
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}
