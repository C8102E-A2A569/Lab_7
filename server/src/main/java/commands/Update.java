package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.StudyGroup;

/**
 * Implements same-named command
 */
public class Update extends Command {

    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public Update(CollectionManager collectionManager) {
        super("update", "update id {element}. Updates element at index");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        int id = Integer.parseInt(argument);
        studyGroup.setId(id);
        StudyGroup sg = collectionManager.findById(id);
        if (sg == null) {
            return "There's no such ID";
        }
        if (!sg.getOwnerName().equals(executorName)) {
            return "Can not modify someone else's groups";
        }
        collectionManager.update(studyGroup);
        return "";
    }
}
