package com.example.eagrotis.dao;

import com.example.eagrotis.entity.Admin;
import com.example.eagrotis.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Employee> getEmployees() {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT u FROM Employee u JOIN u.role r WHERE r.name = 'ROLE_EMPLOYEE'",
                Employee.class
        );
        return query.getResultList();
    }


    @Override
    public Employee getEmployee(Long employee_id) {
        return entityManager.find(Employee.class, employee_id);
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        System.out.println("employee "+ employee.getId());
        if (employee.getId() == null) {
            entityManager.persist(employee);
        } else {
            entityManager.merge(employee);
        }
        return employee;
    }

    @Override
    @Transactional
    public void deleteEmployee(Long employee_id) {
        System.out.println("Deleting employee with id: " + employee_id);
        entityManager.remove(entityManager.find(Employee.class, employee_id));
    }

    /*@Override
    @Transactional
    public List<Assignment> getAssignments(Integer student_id) {
        Student student = entityManager.find(Student.class, student_id);
        return student.getAssignments();
    }*/

}
