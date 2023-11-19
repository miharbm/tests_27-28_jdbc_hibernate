package com.mephi.dao;

import com.mephi.model.User;
import com.mephi.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {

        new UserDaoJDBCImpl().createUsersTable();

    }

    @Override
    public void dropUsersTable() {

        new UserDaoJDBCImpl().dropUsersTable();

    }

    @Override
    public void saveUser(User user) {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save( user );
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {

        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete( new User(id) );
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
//            users = (List<User>) session.createQuery( "From User" ).list();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> rootEntry = cq.from(User.class);
            CriteriaQuery<User> all = cq.select(rootEntry);

            TypedQuery<User> allQuery = session.createQuery(all);
            return allQuery.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {

        String hql = "DELETE FROM User";

        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery( hql ).executeUpdate();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public User getById(BigInteger id){
        User user = null;
        try(Session session = Util.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return user ;
    }
}
