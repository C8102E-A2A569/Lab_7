package commands;

import exceptions.InputDataException;
import exceptions.InvalidArgumentException;
import exceptions.WrongCommandException;
import exchanger.Exchanger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Implements same-named command
 */
public class Clear extends Command {
    private final Exchanger exchanger;

    /**
     * @param exchanger - Exchanger to work with
     */
    public Clear(Exchanger exchanger) {
        super("clear", "Clears collection");
        this.exchanger = exchanger;
    }

    /**
     * @param arguments Inline arguments for command
     * @param br        BufferedReader to get data for classes
     * @param bw        BufferedWriter to write info
     * @return FINISHED, because there's no way to exit from program
     * @throws WrongCommandException
     * @throws InvalidArgumentException
     * @throws IOException
     * @throws InputDataException
     */
    @Override
    public ExitCode impl(String[] arguments, BufferedReader br, BufferedWriter bw, String username, String password) throws WrongCommandException, InvalidArgumentException, IOException {
        if (arguments.length != 0) {
            throw new WrongCommandException("clear", arguments);
        }
        exchanger.exchange("clear", "", null, username, password);
        System.out.println("Cleared");
        return ExitCode.FINISHED;
    }
}
