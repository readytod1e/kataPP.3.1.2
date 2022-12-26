package com.katapp312.dao;

import com.katapp312.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImp implements UserDao{
    private final EntityManager entityManager;

    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public void create(User user) {
        entityManager.joinTransaction();
        entityManager.persist(user);
    }

    @Override
    public User showUserById(long id) {
        TypedQuery<User> query = entityManager.createQuery("from User where id=:i", User.class);
        query.setParameter("i", id);
        return query.getSingleResult();
    }

    @Override
    public List<User> getList() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public void update(long id, User user) {
        entityManager.joinTransaction();
        User u = showUserById(id);
        u.setName(user.getName());
        u.setLast_name(user.getLast_name());
        u.setEmail(user.getEmail());
        entityManager.persist(u);
    }

    @Override
    public void delete(long id) {
        entityManager.joinTransaction();
        try {
            entityManager.remove(showUserById(id));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
