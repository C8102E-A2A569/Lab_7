package commands;

import java.io.IOException;
import java.util.Collections;

import collection.CollectionManager;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class Shuffle extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Shuffle(CollectionManager collectionManager) {
        super("shuffle", "Shuffle elements in collection");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        Collections.shuffle(collectionManager.getCollection());
        return "";
    }
}
