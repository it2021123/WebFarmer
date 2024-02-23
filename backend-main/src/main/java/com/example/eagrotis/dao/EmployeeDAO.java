package com.example.eagrotis.dao;

import com.example.eagrotis.entity.Admin;
import com.example.eagrotis.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> getEmployees();
    public Employee getEmployee(Long employee_id);
    public Employee saveEmployee(Employee employee);
    public void deleteEmployee(Long employee);
}
