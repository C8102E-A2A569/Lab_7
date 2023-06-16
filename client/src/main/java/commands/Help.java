package commands;

import exceptions.InputDataException;
import exceptions.InvalidArgumentException;
import exceptions.WrongCommandException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Implements same-named command
 */
public class Help extends Command {
    private final CommandManager commandManager;

    /**
     * @param commandManager - CommandManager to work on
     */
    public Help(CommandManager commandManager) {
        super("help", "Prints description about all commands");
        this.commandManager = commandManager;
    }


    @Override
    public ExitCode apply(String[] arguments, BufferedReader br, BufferedWriter bw)
            throws WrongCommandException, InvalidArgumentException, IOException, InputDataException {
        return impl(arguments, br, bw, "", "");
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
    public ExitCode impl(String[] arguments, BufferedReader br, BufferedWriter bw, String username, String password) {
        commandManager.getCommands().values().forEach(command -> {
            System.out.println(command.getName() + "\t\t" + command.getDescription());
        });
        return ExitCode.FINISHED;
    }
}
