import org.hibernate.SessionFactory;

import collection.CollectionManager;
import commands.*;
import database.SessionFactoryManager;
import exchanger.Listener;
import executer.Executor;
import users.UserRepository;


public class Main {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = SessionFactoryManager.getSessionFactory();
            CollectionManager collectionManager = new CollectionManager(sessionFactory);
            UserRepository userRepository = new UserRepository(sessionFactory);
            Command.setUserRepository(userRepository);
            collectionManager.sort();

            if (!collectionManager.validate()) {
                System.out.println("Not valid data in db!");
                return;
            }
            var commandManager = new CommandManager() {{
                addCommand(new Info(collectionManager));
                addCommand(new Show(collectionManager));
                addCommand(new Add(collectionManager));
                addCommand(new Update(collectionManager));
                addCommand(new RegisterUser(userRepository));
                addCommand(new RemoveById(collectionManager));
                addCommand(new Clear(collectionManager));
                addCommand(new SaveByClient(collectionManager));
                addCommand(new InsertAtIndex(collectionManager));
                addCommand(new Shuffle(collectionManager));
                addCommand(new RemoveGreater(collectionManager));
                addCommand(new SumOfStudentsCount(collectionManager));
                addCommand(new MaxById(collectionManager));
                addCommand(new CountByFormOfEducation(collectionManager));
            }};

            Listener listener = new Listener(commandManager);
            Thread handleCommandsThread = new Thread(listener::handleCommands);
            handleCommandsThread.start();

            Executor executor = new Executor(new Save(collectionManager));
            Thread interactiveRunThread = new Thread(executor::interactiveRun);
            interactiveRunThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
