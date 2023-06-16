package commands;

import data.EnumParser;
import data.FormOfEducation;
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
public class CountByFormOfEducation extends Command {
    private final Exchanger exchanger;

    /**
     * @param exchanger - Exchanger to work with
     */
    public CountByFormOfEducation(Exchanger exchanger) {
        super("count_by_form_of_education", "Counts number of groups with some form of education");
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
        if (arguments.length != 1) {
            throw new WrongCommandException(getName(), arguments);
        }
        FormOfEducation formOfEducation = EnumParser.textToFormOfEducation.get(arguments[0]);
        String counter = exchanger.exchange("count_by_form_of_education", formOfEducation.name(), null, username, password);
        System.out.println(counter);
        return ExitCode.FINISHED;
    }
}
