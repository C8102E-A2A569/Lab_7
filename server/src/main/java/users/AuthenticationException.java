package users;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public static AuthenticationException UnknownUser(String username) {
        return new AuthenticationException(String.format("User with name '%s' is not registered", username));
    }

    public static AuthenticationException InvalidPassword() {
        return new AuthenticationException("Invalid password");
    }

    public static AuthenticationException UsernameAlreadyExists(String username) {
        return new AuthenticationException(String.format("User with name '%s' already exists", username));
    }
}
