package com.demo.join.demo.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.join.demo.Entity.Employees;
import com.demo.join.demo.Entity.Project;
import com.demo.join.demo.Resposiotry.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project){
        return projectRepository.save(project);
    }
    public List<Project> getAllProject(){
        return projectRepository.findAll();
    }
    public Optional<Project> findById(int project_id){
        return projectRepository.findById(project_id);
    }
}
