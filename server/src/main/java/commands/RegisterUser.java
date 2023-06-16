package commands;

import java.io.IOException;

import data.StudyGroup;
import users.AuthenticationException;
import users.UserRepository;

public class RegisterUser extends Command {
    private UserRepository userRepository;
    public RegisterUser(UserRepository userRepository) {
        super("register", "Register a new user");
        this.userRepository = userRepository;
    }

    @Override
    public String apply(String argument, StudyGroup studyGroup, String executorName, String password)
        throws IOException {
        try {
            userRepository.registerUser(executorName, password);
        }
        catch (AuthenticationException e) {
            return e.getMessage();
        }
        return "Registered";
    }

    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        return "";
    }
}
