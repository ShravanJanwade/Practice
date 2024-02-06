package com.demo.join.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.join.demo.Entity.Employees;
import com.demo.join.demo.Entity.Project;
import com.demo.join.demo.Resposiotry.EmployeeRepository;
import com.demo.join.demo.Resposiotry.ProjectRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    public List<Employees> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employees createEmployees(Employees employees){
        return employeeRepository.save(employees);
    }
    public List<Employees> getEmployeeByProjectId(int projectId){
        return employeeRepository.findByProjectId(projectId);
    }
    public Project getEmployeesByProjectId(int projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }
}
