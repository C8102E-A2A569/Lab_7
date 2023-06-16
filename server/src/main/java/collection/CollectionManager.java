package collection;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import data.StudyGroup;
import data.Validatable;

/**
 * Implements collection
 */
public class CollectionManager implements Validatable {
    private Vector<StudyGroup> collection;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private SessionFactory sessionFactory;
    private ReadWriteLock rwLock;

    /**
     * @throws IOException
     */
    public CollectionManager(SessionFactory sessionFactory) throws IOException, ParseException {
        this.sessionFactory = sessionFactory;
        Session session = sessionFactory.openSession();
        List<StudyGroup> groups = session.createQuery("SELECT sg FROM StudyGroup sg", StudyGroup.class).list();

        collection = new Vector<>(groups);
        lastInitTime = LocalDateTime.now();
        rwLock = new ReentrantReadWriteLock();
    }

    public StudyGroup findById(int id) {
        for (var entry : collection) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    public void add(StudyGroup studyGroup, String creator) {
        studyGroup.setOwnerName(creator);
        rwLock.writeLock().lock();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(studyGroup);
        transaction.commit();
        session.close();


        collection.add(studyGroup);

        lastSaveTime = LocalDateTime.now();
        rwLock.writeLock().unlock();
    }

    public void update(StudyGroup studyGroup) {
        rwLock.writeLock().lock();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(studyGroup);
        transaction.commit();
        session.close();

        StudyGroup sg = findById(studyGroup.getId());
        sg.setName(studyGroup.getName());
        sg.setCoordinates(studyGroup.getCoordinates());
        sg.setCreationDate(studyGroup.getCreationDate());
        sg.setStudentsCount(studyGroup.getStudentsCount());
        sg.setFormOfEducation(studyGroup.getFormOfEducation());
        sg.setSemesterEnum(studyGroup.getSemesterEnum());
        sg.setGroupAdmin(studyGroup.getGroupAdmin());

        lastSaveTime = LocalDateTime.now();
        rwLock.writeLock().unlock();
    }

    public void remove(int id) {
        rwLock.writeLock().lock();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        StudyGroup studyGroup = findById(id);
        session.remove(studyGroup);
        transaction.commit();
        session.close();

        collection.remove(studyGroup);

        lastSaveTime = LocalDateTime.now();
        rwLock.writeLock().unlock();
    }

    public void removeAll(String owner) {
        rwLock.writeLock().lock();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createMutationQuery("DELETE StudyGroup WHERE ownerName = :owner")
            .setParameter("owner", owner).executeUpdate();
        transaction.commit();

        Vector<StudyGroup> sifted = new Vector<>();
        for (var sg : collection) {
            if (!sg.getOwnerName().equals(owner)) {
                sifted.add(sg);
            }
        }

        collection = sifted;
        rwLock.writeLock().unlock();
    }

    public void removeGreater(StudyGroup studyGroup, String owner) {
        Vector<StudyGroup> sifted = new Vector<>();
        for (var sg : collection) {
            if (sg.getOwnerName().equals(owner) && studyGroup.compareTo(sg) < 0) {
                remove(sg.getId());
            }
        }

        collection = sifted;
    }

    /**
     * @return true if all collection entries are valid
     */
    @Override
    public boolean validate() {
        rwLock.readLock().lock();
        for (var sg : collection) {
            if (!sg.validate()) {
                rwLock.readLock().unlock();
                return false;
            }
        }
        rwLock.readLock().unlock();
        return true;
    }

    /**
     * @return String with applied toString() for all collection entries
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        rwLock.readLock().lock();
        for (var sg : collection) {
            sb.append(sg.toString());
            sb.append("\n\n");
        }
        rwLock.readLock().unlock();
        return sb.toString();
    }


    /**
     * Saves collection to file given in constructor
     * @throws IOException
     */
    public void save() throws IOException {
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * @return Collection
     */
    public Vector<StudyGroup> getCollection() {
        return collection;
    }

    /**
     * @return Last time of initialization
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Last time collection has been saved
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Sorts collection
     */
    public void sort() {
        rwLock.writeLock().lock();
        collection.sort(StudyGroup::compareTo);
        rwLock.writeLock().unlock();
    }
}
