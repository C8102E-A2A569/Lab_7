package commands;

import exceptions.InputDataException;
import exceptions.InvalidArgumentException;
import exceptions.WrongCommandException;
import exchanger.Exchanger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Stops the client application
 */
public class Exit extends Command {
    private final Exchanger exchanger;

    /**
     * @param exchanger - Exchanger to work with
     */
    public Exit(Exchanger exchanger) {
        super("exit", "stops the client application");
        this.exchanger = exchanger;
    }

    /**
     * @param arguments Inline arguments for command
     * @param br        BufferedReader to get data for classes
     * @param bw        BufferedWriter to write info
     * @throws WrongCommandException
     * @throws InvalidArgumentException
     * @throws IOException
     * @throws InputDataException
     */
    @Override
    public ExitCode impl(String[] arguments, BufferedReader br, BufferedWriter bw, String username, String password) throws WrongCommandException, InvalidArgumentException, IOException, InputDataException {
        if (arguments.length != 0) {
            throw new WrongCommandException(getName(), arguments);
        }
        exchanger.exchange("save_by_client", "", null, username, password);
        System.out.println("Collection saved!");
        return ExitCode.EXIT;
    }
}
