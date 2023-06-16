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
public class Shuffle extends Command {
    private final Exchanger exchanger;

    /**
     * @param exchanger - Exchanger to work with
     */
    public Shuffle(Exchanger exchanger) {
        super("shuffle", "Shuffle elements in collection");
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
    public ExitCode impl(String[] arguments, BufferedReader br, BufferedWriter bw, String username, String password) throws WrongCommandException, InvalidArgumentException, IOException, InputDataException {
        if (arguments.length != 0) {
            throw new WrongCommandException("shuffle", arguments);
        }
        exchanger.exchange("shuffle", "", null, username, password);
        return ExitCode.FINISHED;
    }
}
