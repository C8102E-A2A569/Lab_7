package users;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserRepository {
    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) throws IOException {
        this.sessionFactory = sessionFactory;
    }

    public void registerUser(String username, String password) {
        Session session = sessionFactory.openSession();
        if (session.byId(User.class).load(username) != null) {
            throw AuthenticationException.UsernameAlreadyExists(username);
        }

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String ssalt = new String(salt, StandardCharsets.UTF_8);
        String hashedPassword = hashPassword(password, ssalt);
        User user = new User(username, hashedPassword, ssalt);
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    public void checkUser(String username, String password) {
        Session session = sessionFactory.openSession();
        User user = session.byId(User.class).load(username);
        if (user == null) {
            throw AuthenticationException.UnknownUser(username);
        }
        if (!user.getPasswordHash().equals(hashPassword(password, user.getPasswordSalt()))) {
            throw AuthenticationException.InvalidPassword();
        }
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            return new String(md.digest(password.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No algorithm.");
            return "";
        }
    }
}
