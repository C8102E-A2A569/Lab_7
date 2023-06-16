package commands;

import java.util.HashMap;


/**
 * Just contains all commands
 */
public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();


    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }


}
