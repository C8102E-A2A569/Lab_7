package executer;

import commands.Command;

import java.io.*;


/**
 * Class works on command from user
 */
public class Executor {
    private final Command saveCommand;

    public Executor(Command saveCommand) {
        this.saveCommand = saveCommand;
    }

    /**
     * Interactive work with user
     *
     * @throws IOException
     */
    public void interactiveRun() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

            if (commandName.equals("save")) {
                try {
                    saveCommand.apply("", null, "", "");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
