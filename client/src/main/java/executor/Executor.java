package executor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import commands.Command;
import commands.CommandManager;
import commands.ExitCode;
import exceptions.InputDataException;
import exceptions.InvalidArgumentException;
import exceptions.WrongCommandException;


/**
 * Class works on command from user
 */
public class Executor {
    private final CommandManager commandManager;

    public Executor(CommandManager commandManager) {
        this.commandManager = commandManager;
    }


    /**
     * Interactive work with user
     *
     * @throws IOException
     */
    public void interactiveRun() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] command_call = line.split(" ");
            if (command_call.length == 0) {
                System.out.println("Enter command please!");
                continue;
            }

            String commandName = command_call[0];
            String[] arguments = {};
            if (command_call.length > 1) {
                arguments = new String[command_call.length - 1];
                System.arraycopy(command_call, 1, arguments, 0, command_call.length - 1);
            }

            try {
                if (executeCommand(commandName, arguments) == ExitCode.EXIT) {
                    return;
                }
            } catch (NullPointerException ex) {
                break;
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }

    }


    /**
     * @param commandName Command to execute
     * @param arguments   Inline arguments
     * @return Returns ExitCode
     * @throws WrongCommandException
     * @throws InvalidArgumentException
     * @throws IOException
     * @throws InputDataException
     */
    private ExitCode executeCommand(String commandName, String[] arguments) throws WrongCommandException, InvalidArgumentException, IOException, InputDataException {
        Command command = commandManager.getCommands().get(commandName);
        if (command == null) {
            System.out.println("There's no such command: '" + commandName + "'");
            return ExitCode.FINISHED;
        }
        return command.apply(arguments, new BufferedReader(new InputStreamReader(System.in)), new BufferedWriter(new OutputStreamWriter(System.out)));
    }
}
