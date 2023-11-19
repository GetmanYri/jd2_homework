package getman.homework.data.dao.DaoForTask6;

import getman.homework.data.pojo.forTask6.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GeneratedIdDao {
    SessionFactory sessionFactory;

    public GeneratedIdDao(SessionFactory sessionFactory) {
        if (sessionFactory == null) throw new IllegalStateException("sessionFactory cannot be null");
        this.sessionFactory = sessionFactory;
    }


    public String saveAndGetUUID(IdUuid uuid) {
        Session session = null;
        Transaction transaction = null;
        String saveId;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(uuid);
            session.flush();
            session.refresh(uuid);
            saveId = (String) session.getIdentifier(uuid);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return saveId;
    }

    public String saveAndGetIdGuid(IdGUID idGUID) {
        Session session = null;
        Transaction transaction = null;
        String saveId;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(idGUID);
            session.flush();
            session.refresh(idGUID);
            saveId = (String) session.getIdentifier(idGUID);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return saveId;
    }

    public long saveAndGetHiLo(IdHiLo idHiLo) {
        Session session = null;
        Transaction transaction = null;
        long id;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(idHiLo);
            session.flush();
            session.refresh(idHiLo);

            id = (long) session.getIdentifier(idHiLo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return id;
    }

    public long saveAndGetIdentity(IdIdentity idIdentity) {
        Session session = null;
        Transaction transaction = null;
        long id;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(idIdentity);
            session.flush();
            session.refresh(idIdentity);

            id = (long) session.getIdentifier(idIdentity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return id;
    }

    public long saveAndGetIdSequence(IdSequence idSequence) {
        Session session = null;
        Transaction transaction = null;
        long id;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(idSequence);
            session.flush();
            session.refresh(idSequence);

            id = (long) session.getIdentifier(idSequence);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            if (session != null) session.close();
        }
        return id;
    }

}
