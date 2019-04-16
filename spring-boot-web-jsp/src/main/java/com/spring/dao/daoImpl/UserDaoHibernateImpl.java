package com.spring.dao.daoImpl;


import com.spring.dao.UserDao;
import com.spring.model.Role;
import com.spring.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User application) {
        ArrayList<Role> roles = new ArrayList<>();
        Role role = (Role) em.createQuery(
                "FROM Role c WHERE c.name LIKE :user")
                .setParameter("user", "USER")
                .getSingleResult();
        roles.add(role);
        application.setRoles(roles);
        try {
            em.createQuery(
                    "FROM User c WHERE c.name LIKE :user")
                    .setParameter("user", application.getName())
                    .getSingleResult();

        } catch (NonUniqueResultException e) {
            em.merge(application);
        } catch (NoResultException e) {
            em.persist(application);
        }

    }

    //каскадирование

    @Override
    public void deleteUser(int userId) {

        em.remove(getUserById(userId));
    }

    @Override
    public void updateUser(User application) {
        em.merge(application);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserById(int userId) {

        return em.find(User.class, userId);
    }

    @Override
    public User getUserByLogin(String name) {
        return (User) em.createQuery(
                "FROM User c WHERE c.name LIKE :user")
                .setParameter("user", name)
                .getSingleResult();

    }
}