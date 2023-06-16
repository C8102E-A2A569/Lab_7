package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class RemoveById extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id", "remove_by_id id. Removes element with equal id");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        int id = Integer.parseInt(argument);
        StudyGroup group = collectionManager.findById(id);
        if (group == null) {
            return "There's no such element!";
        }
        if (!group.getOwnerName().equals(executorName)) {
            return "Can not modify someone else's groups";
        }
        collectionManager.remove(id);
        return "Removed!";
    }
}
