package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

@Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createSQLQuery("create table if NOT EXISTS users\n" +
                    "(\n" +
                    "    id       bigint auto_increment\n" +
                    "        primary key,\n" +
                    "    age      tinyint      null,\n" +
                    "    lastName varchar(255) null,\n" +
                    "    name     varchar(255) null\n" +
                    ")\n" +
                    "    engine = MyISAM;\n");
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }

    }
    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("DROP Table IF EXISTS users");
            query.executeUpdate();
            transaction.commit();
            session.close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {

            Query query = session.createSQLQuery("INSERT INTO users (name, lastName, age) "
                    + "VALUES(?, ?, ?)");
            query.setParameter(1, name);
            query.setParameter(2, lastName);
            query.setParameter(3, age);
            query.executeUpdate();
            //session.persist(query);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("DELETE FROM users WHERE ID = ?");
            query.setParameter(1, id);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }


    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> list = new ArrayList<>();
        Transaction transaction = session.beginTransaction();

        try {
            session.get(User.class, 1L);
            Query query = session.createQuery("from User");
            list  = query.getResultList();
            session.close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return list;
    }


    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction  transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("DELETE FROM users");
            query.executeUpdate();
            transaction.commit();
            session.close();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
