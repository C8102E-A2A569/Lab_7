import commands.*;
import exchanger.Exchanger;
import executor.Executor;

public class Main {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        try {
            var commandManager = new CommandManager() {{
                addCommand(new Exit(exchanger));
                addCommand(new Test(exchanger));
                addCommand(new Help(this));
                addCommand(new Info(exchanger));
                addCommand(new Show(exchanger));
                addCommand(new Add(exchanger));
                addCommand(new Update(exchanger));
                addCommand(new RegisterUser(exchanger));
                addCommand(new RemoveById(exchanger));
                addCommand(new Clear(exchanger));
                addCommand(new ExecuteScript(this));
                addCommand(new InsertAtIndex(exchanger));
                addCommand(new Shuffle(exchanger));
                addCommand(new RemoveGreater(exchanger));
                addCommand(new SumOfStudentsCount(exchanger));
                addCommand(new MaxById(exchanger));
                addCommand(new CountByFormOfEducation(exchanger));
            }};

            if (args.length > 0 && args[0].equals("execute_script")) {
                if (args.length < 4) {
                    System.out.println("Not enough arguments");
                    return;
                }
                ExecuteScript execute = (ExecuteScript)commandManager.getCommands().get("execute_script");
                execute.applyNonInteractively(args[1], args[2], args[3]);
            }
            Executor executor = new Executor(commandManager);
            executor.interactiveRun();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

