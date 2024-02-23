package com.example.eagrotis.dao;

import com.example.eagrotis.entity.Admin;
import com.example.eagrotis.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AminDAOImpl implements AdminDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Admin> getAdmins() {
        TypedQuery<Admin> query = entityManager.createQuery("SELECT u FROM Admin u JOIN u.role r WHERE r.name = 'ROLE_ADMIN'", Admin.class);
        return query.getResultList();
    }

    @Override
    public Admin getAdmin(Long admin_id) {
        return entityManager.find(Admin.class, admin_id);
    }

    @Override
    @Transactional
    public Admin saveAdmin(Admin admin) {
        System.out.println("admin "+ admin.getId());
        if (admin.getId() == null) {
            entityManager.persist(admin);
        } else {
            entityManager.merge(admin);
        }
        return admin;
    }

    @Override
    @Transactional
    public void deleteAdmin(Long admin_id) {
        System.out.println("Deleting admin with id: " + admin_id);
        entityManager.remove(entityManager.find(Admin.class, admin_id));
    }

    /*@Override
    @Transactional
    public List<Assignment> getAssignments(Integer student_id) {
        Student student = entityManager.find(Student.class, student_id);
        return student.getAssignments();
    }*/

}
