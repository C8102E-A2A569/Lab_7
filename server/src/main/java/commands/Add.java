package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class Add extends Command {

    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Add(CollectionManager collectionManager) {
        super("add", "add {element}. Adds new study group to collection");
        this.collectionManager = collectionManager;
    }


    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        collectionManager.add(studyGroup, executorName);
        return "";
    }
}
