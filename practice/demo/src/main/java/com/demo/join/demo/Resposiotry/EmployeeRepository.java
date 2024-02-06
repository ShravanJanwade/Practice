package com.demo.join.demo.Resposiotry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.join.demo.Entity.Employees;

public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
    List<Employees> findByProjectId(int projectId);
}
