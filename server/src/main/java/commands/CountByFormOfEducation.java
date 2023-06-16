package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.EnumParser;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class CountByFormOfEducation extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public CountByFormOfEducation(CollectionManager collectionManager) {
        super("count_by_form_of_education", "Counts number of groups with some form of education");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        var formOfEducation = EnumParser.textToFormOfEducation.get(argument);
        int counter = 0;
        for (var sg : collectionManager.getCollection()) {
            if (sg.getFormOfEducation() == formOfEducation) {
                ++counter;
            }
        }

        return String.valueOf(counter);
    }
}
