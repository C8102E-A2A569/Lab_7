package commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import exceptions.InputDataException;
import exceptions.InvalidArgumentException;
import exceptions.WrongCommandException;
import exchanger.Exchanger;

public class RegisterUser extends Command {

    private final Exchanger exchanger;

    public RegisterUser(Exchanger exchanger) {
        super("register", "Register a new user");
        this.exchanger = exchanger;
    }

    @Override
    protected ExitCode impl(String[] arguments, BufferedReader br, BufferedWriter bw, String username, String password)
            throws WrongCommandException, InvalidArgumentException, IOException, InputDataException {
        if (bw != null) {
            bw.write("Enter password again: ");
            bw.flush();
        }
        if (!password.equals(br.readLine())) {
            if (bw != null) {
                bw.write("Failed to create user, please try again\n");
                bw.flush();
            }
            return ExitCode.FINISHED;
        }

        String answer = exchanger.exchange("register", "", null, username, password);
        System.out.println(answer);
        return ExitCode.FINISHED;
    }
}
