package commands;

import java.io.IOException;
import java.util.Objects;

import data.StudyGroup;
import users.AuthenticationException;
import users.UserRepository;


/**
 * Describe Command interface. All commands have to have 'apply()' method
 */
public abstract class Command {
    private final String name;
    private final String description;
    private static UserRepository userRepository;

    public static void setUserRepository(UserRepository userRepository) {
        Command.userRepository = userRepository;
    }

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected abstract String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException;

    public String apply(String argument, StudyGroup studyGroup, String executorName, String password) throws IOException {
        try {
            userRepository.checkUser(executorName, password);
        }
        catch (AuthenticationException e) {
            return e.getMessage();
        }
        return impl(argument, studyGroup, executorName);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Command command = (Command) other;
        return Objects.equals(name, command.name) && Objects.equals(description, command.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
