package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import data.StudyGroup;
import users.User;

public class SessionFactoryManager {
    public static SessionFactory getSessionFactory() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("db.cfg"));
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addPackage("data");
        configuration.addAnnotatedClass(StudyGroup.class);
        configuration.addPackage("users");
        configuration.addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
}
