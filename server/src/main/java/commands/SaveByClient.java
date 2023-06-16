package commands;

import java.io.IOException;

import collection.CollectionManager;
import data.StudyGroup;

public class SaveByClient extends Command {
    private final CollectionManager collectionManager;

    /**
     * @param collectionManager - Collection to work on
     */
    public SaveByClient(CollectionManager collectionManager) {
        super("save_by_client", "Saves collection to file by client call");
        this.collectionManager = collectionManager;
    }

    /**
     * @return FINISHED, because there's no way to exit from program
     * @throws IOException
     */
    @Override
    protected String impl(String argument, StudyGroup studyGroup, String executorName) throws IOException {
        collectionManager.save();
        return "";
    }
}
