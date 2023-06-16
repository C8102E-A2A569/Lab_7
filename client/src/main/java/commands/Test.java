package commands;

import data.*;
import exceptions.InputDataException;
import exceptions.InvalidArgumentException;
import exceptions.WrongCommandException;
import exchanger.Exchanger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Just for testing
 */
public class Test extends Command {
    private final Exchanger exchanger;

    /**
     * @param exchanger - Exchanger to work with
     */
    public Test(Exchanger exchanger) {
        super("test", "testing add command");
        this.exchanger = exchanger;
    }

    @Override
    public ExitCode impl(String[] arguments, BufferedReader br, BufferedWriter bw, String username, String password) throws WrongCommandException, InvalidArgumentException, IOException, InputDataException {
        if (arguments.length != 0) {
            throw new WrongCommandException("test", arguments);
        }
        StudyGroup sg = new StudyGroup(
                "name1",
                new Coordinates(1L, 1L), 1L, FormOfEducation.DISTANCE_EDUCATION,
                Semester.FIRST,
                new Person("name2", 1.0, 1.0, "1654814587325481453", new Location(1, 1L, 1, "name3")),
                new Date()
        );
        exchanger.exchange("add", "", sg, username, password);
        return ExitCode.FINISHED;
    }
}
