package com.demo.join.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.demo.join.demo.Resposiotry.ProjectRepository;
import com.demo.join.demo.Service.EmployeeService;
import com.demo.join.demo.Service.ProjectService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;

    @PostMapping("addEmployee/{project_id}")
    public ResponseEntity<Employees>  createEmployee(@RequestBody Employees employee,@PathVariable int project_id) {
        employee.setProject(new Project(project_id,"","",new ArrayList<>()));
        Employees savedEmployees=employeeService.createEmployees(employee);
        return new ResponseEntity<>(savedEmployees,HttpStatus.CREATED);
    }
        
    @GetMapping("allEmployees")
    public ResponseEntity<List<Employees>> getAll(){
        List<Employees> allEmployees= employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }
    @GetMapping("{projectId}/allEmployees")
    public List<Employees> allProjectEmployees(@PathVariable int projectId){

        List<Employees> allProjectEmployees=employeeService.getEmployeeByProjectId(projectId);
        return allProjectEmployees;
    }

    @PostMapping("/{projectId}/addEmployees")
public ResponseEntity<Employees> addEmployeeToProject(@PathVariable int projectId, @RequestBody Employees employee) {
    Optional<Project> projectOptional = projectService.findById(projectId);
    if (!projectOptional.isPresent()) {
        return ResponseEntity.notFound().build();
    }
    Project project = projectOptional.get();
    employee.setProject(project); // Set the project reference for the employee
    Employees savedEmployee = employeeService.createEmployees(employee);
    return ResponseEntity.ok(savedEmployee);
}
    @GetMapping("/project/{projectId}")
    public Project getEmployeesByProjectId(@PathVariable int projectId) {
        return employeeService.getEmployeesByProjectId(projectId);
    }

}
