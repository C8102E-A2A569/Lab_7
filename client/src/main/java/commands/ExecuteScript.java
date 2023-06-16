package commands;

import exceptions.InputDataException;
import exceptions.InvalidArgumentException;
import exceptions.WrongCommandException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements same-named command
 */
public class ExecuteScript extends Command {
    private final CommandManager commandManager;

    /**
     * @param commandManager - commandManager to work on
     */
    public ExecuteScript(CommandManager commandManager) {
        super("execute_script", "Executes commands from file");
        this.commandManager = commandManager;
    }

    public ExitCode applyNonInteractively(String file, String username, String password) throws WrongCommandException, InvalidArgumentException, IOException, InputDataException {
        return impl(new String[] {file}, null, null, username, password);
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
        if (arguments.length != 1) {
            throw new WrongCommandException("execute_script", arguments);
        }
        String line;
        BufferedReader fileInput = new BufferedReader(new FileReader(arguments[0]));
        while ((line = fileInput.readLine()) != null) {
            String[] command_call = line.split(" ");
            if (command_call.length == 0) {
                fileInput.close();
                throw new InputDataException("Empty line found!");
            }

            String commandName = command_call[0];
            if (Objects.equals(commandName, "exit")) {
                fileInput.close();
                if (command_call.length > 1) {
                    throw new InputDataException("Exit doesn't allow any arguments!");
                }
                return ExitCode.EXIT;
            }

            String[] args = {};
            if (command_call.length > 1) {
                args = new String[command_call.length - 1];
                System.arraycopy(command_call, 1, args, 0, command_call.length - 1);
            }

            Command command = commandManager.getCommands().get(commandName);
            if (command == null) {
                System.out.println("There's no such command: '" + commandName + "'");
                continue;
            }
            command.impl(args, fileInput, null, username, password);
        }
        return ExitCode.FINISHED;
    }

}
