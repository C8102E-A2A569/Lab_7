package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class Show extends Command {

    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Show(CollectionManager collectionManager) {
        super("show", "Prints collection data");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (var sg : collectionManager.getCollection()) {
            sb.append(sg.toString()).append("\n\n");
        }
        return sb.toString();
    }
}
