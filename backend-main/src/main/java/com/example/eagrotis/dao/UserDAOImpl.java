package com.example.eagrotis.dao;

import com.example.eagrotis.dto.UserDTO;
import com.example.eagrotis.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;


    // Users with their roles
    @Override
    @Transactional
    public List<Object[]> getUsers() {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT u, r.name FROM User u JOIN u.role r", Object[].class
        );
        return query.getResultList();
    }

    @Override
    public User getUser(Long user_id) {
        String jpql = "SELECT u FROM User u JOIN FETCH u.role WHERE u.id = :userId";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("userId", user_id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle the case where no user with the given ID is found
        }
    }

    //Created to also see the role
    @Override
    public UserDTO getUserDTO(Long userId) {
        String jpql = "SELECT u FROM User u JOIN FETCH u.role WHERE u.id = :userId";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("userId", userId);

        try {
            User user = query.getSingleResult();
            return user != null ? user.toDTO() : null;
        } catch (NoResultException e) {
            return null; // Handle the case where no user with the given ID is found
        }
    }


    @Override
    @Transactional
    public User saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            // If you update the role, merge the role separately to avoid re-inserting the role
            if (user.getRole() != null && user.getRole().getId() == null) {
                entityManager.persist(user.getRole());
            }
            entityManager.merge(user);
        }
        return user;
    }


    @Override
    @Transactional
    public void deleteUser(Long user_id) {
        System.out.println("Deleting user with id: " + user_id);
        entityManager.remove(entityManager.find(User.class, user_id));
    }

    /*@Override
    @Transactional
    public List<Assignment> getAssignments(Integer student_id) {
        Student student = entityManager.find(Student.class, student_id);
        return student.getAssignments();
    }*/

}
