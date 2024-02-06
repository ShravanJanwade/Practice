package com.demo.join.demo.Controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.join.demo.Entity.Employees;
import com.demo.join.demo.Entity.Project;
import com.demo.join.demo.Service.EmployeeService;
import com.demo.join.demo.Service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @PostMapping
    public ResponseEntity<Project>  createProject(@RequestBody Project project) {
        Project savedProject=projectService.createProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(){
        List<Project> projects = projectService.getAllProject();
        return  new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employees>> getEmployeesForProject(@PathVariable int id) {
        Optional<Project> projectOptional = projectService.findById(id);
        if (!projectOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Project project = projectOptional.get();
        Hibernate.initialize(project.getEmployees());
        return ResponseEntity.ok(project.getEmployees());
    }

}
