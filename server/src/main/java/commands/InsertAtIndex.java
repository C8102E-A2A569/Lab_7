package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class InsertAtIndex extends Command {

    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public InsertAtIndex(CollectionManager collectionManager) {
        super("insert_at", "insert_at index {element}");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        var index = Integer.parseInt(argument);
        collectionManager.getCollection().insertElementAt(studyGroup, index);
        collectionManager.add(studyGroup, executorName);
        return "";
    }
}
