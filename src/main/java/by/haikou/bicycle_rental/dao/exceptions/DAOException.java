package by.haikou.bicycle_rental.dao.exceptions;

public class DAOException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    DAOException() {
        super();
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
