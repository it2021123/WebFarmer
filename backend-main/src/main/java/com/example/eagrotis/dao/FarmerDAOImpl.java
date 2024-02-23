package com.example.eagrotis.dao;

import com.example.eagrotis.entity.Admin;
import com.example.eagrotis.entity.Farmer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FarmerDAOImpl implements FarmerDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Farmer> getFarmers() {
        TypedQuery<Farmer> query = entityManager.createQuery("SELECT u FROM Farmer u JOIN u.role r WHERE r.name = 'ROLE_FARMER'", Farmer.class);
        return query.getResultList();
    }

    @Override
    public Farmer getFarmer(Long farmer_id) {
        return entityManager.find(Farmer.class, farmer_id);
    }

    @Override
    @Transactional
    public Farmer saveFarmer(Farmer farmer) {
        System.out.println("farmer "+ farmer.getId());
        if (farmer.getId() == null) {
            entityManager.persist(farmer);
        } else {
            entityManager.merge(farmer);
        }
        return farmer;
    }

    @Override
    @Transactional
    public void deleteFarmer(Long farmer_id) {
        System.out.println("Deleting farmer with id: " + farmer_id);
        entityManager.remove(entityManager.find(Farmer.class, farmer_id));
    }

    /*@Override
    @Transactional
    public List<Assignment> getAssignments(Integer student_id) {
        Student student = entityManager.find(Student.class, student_id);
        return student.getAssignments();
    }*/

}
