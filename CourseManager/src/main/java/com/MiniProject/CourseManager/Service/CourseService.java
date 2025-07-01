package com.MiniProject.CourseManager.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MiniProject.CourseManager.Entity.CourseDetails;
import com.MiniProject.CourseManager.Repository.CourseRepo;

@Service
public class CourseService {
    
	@Autowired
	private CourseRepo repo;
	
	public boolean addCourse(CourseDetails course) {
		CourseDetails coursedet = repo.save(course);
		if(coursedet != null) {
			return true;
		}
		return false;
	}
	
	
	
	public List<CourseDetails> getCourses(){
		List<CourseDetails> list = repo.findAll();
		return list;
	}
	
	
	
	public CourseDetails getById(String id) {
		CourseDetails coursedet = repo.findById(id).orElse(null);
		return coursedet;
	}
	
	public boolean deleteCourseById(String id) {
	    if (repo.existsById(id)) {
	    	repo.deleteById(id);
	        return true;
	    }
	    return false;
	}
}
