package com.demo.join.demo.Resposiotry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.join.demo.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Integer>{
    
}
