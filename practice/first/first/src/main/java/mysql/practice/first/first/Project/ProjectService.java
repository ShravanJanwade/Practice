package mysql.practice.first.first.Project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import mysql.practice.first.first.Employees.Employee;
import mysql.practice.first.first.Exception.ResourseNotFoundException;
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project addProject(Project project) {
         return projectRepository.save(project); 
    }
    public Optional<Project> getProjectById(int id){
        return projectRepository.findById(id);
    }
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
    public List<Employee> getAllEmployeeByProjectId(int projectId){
        Optional<Project> project=getProjectById(projectId);
        List<Employee> allEmployees=project.get().getEmployees();
        return allEmployees;
    }


}
