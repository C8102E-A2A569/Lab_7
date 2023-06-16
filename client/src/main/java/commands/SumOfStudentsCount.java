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
public class SumOfStudentsCount extends Command {
    private final Exchanger exchanger;

    /**
     * @param exchanger - Exchanger to work with
     */
    public SumOfStudentsCount(Exchanger exchanger) {
        super("sum_of_students_count", "Sums all students count");
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
        String answer = exchanger.exchange("sum_of_students_count", "", null, username, password);
        System.out.println(answer);
        return ExitCode.FINISHED;
    }
}
