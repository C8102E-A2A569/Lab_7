package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class Clear extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Clear(CollectionManager collectionManager) {
        super("clear", "Clears collection");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        collectionManager.removeAll(executorName);
        return "";
    }
}
