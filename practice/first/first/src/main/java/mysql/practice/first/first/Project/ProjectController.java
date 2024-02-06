package mysql.practice.first.first.Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mysql.practice.first.first.Employees.Employee;
import mysql.practice.first.first.Employees.EmployeeService;

@RestController
@RequestMapping("/Projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody  Project project){
        Project ouProject= projectService.addProject(project);
        return new ResponseEntity<>(ouProject,HttpStatus.CREATED);
    }

   @PostMapping("{id}/addEmployee")
public ResponseEntity<Employee> addEmployeeByProjectId(@PathVariable("id") int projectId, @RequestBody Employee employee) {
        Project project = projectService.getProjectById(projectId).get();
        employee.setProject(project);
        employeeService.addEmployee(employee);
        List<Employee> employeeList=new ArrayList<>(project.getEmployees());
        employeeList.add(employee);
        project.setEmployees(employeeList);
        return ResponseEntity.ok(employee);
}

    @GetMapping("{id}/allEmployees")
    public ResponseEntity<List<Employee>> getAllProjectByProjectId(@PathVariable("id") int projectId){
        List<Employee> employees = projectService.getAllEmployeeByProjectId(projectId);
        return ResponseEntity.ok(employees);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(){
        List<Project> allProjects=projectService.getAllProjects();
        return ResponseEntity.ok(allProjects);
    }
}
