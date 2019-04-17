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
    public void addUser(User user) {
        ArrayList<Role> roles = new ArrayList<>();
        Role role = (Role) em.createQuery(
                "FROM Role c WHERE c.name LIKE :user")
                .setParameter("user", "USER")
                .getSingleResult();
        roles.add(role);
        user.setRoles(roles);
        try {
            em.createQuery(
                    "FROM User c WHERE c.name LIKE :user")
                    .setParameter("user", user.getName())
                    .getSingleResult();

        } catch (NonUniqueResultException e) {
            em.merge(user);
        } catch (NoResultException e) {
            em.persist(user);
        }
    }

    @Override
    public void deleteUser(int userId) {

        em.remove(getUserById(userId));
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
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
    public User getUserByLogin(String login) {
        return (User) em.createQuery(
                "FROM User c WHERE c.name LIKE :user")
                .setParameter("user", login)
                .getSingleResult();

    }
}