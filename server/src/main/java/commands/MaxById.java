package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class MaxById extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public MaxById(CollectionManager collectionManager) {
        super("max_by_id", "Prints object with max ID");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        StudyGroup maxGroup = null;
        for (var sg : collectionManager.getCollection()) {
            if (maxGroup == null || sg.getId() > maxGroup.getId()) {
                maxGroup = sg;
            }
        }
        if (maxGroup == null) {
            return "Collection is empty!";
        } else {
            return maxGroup.toString();
        }
    }
}
