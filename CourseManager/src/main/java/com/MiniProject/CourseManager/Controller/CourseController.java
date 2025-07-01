package com.MiniProject.CourseManager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MiniProject.CourseManager.Entity.CourseDetails;
import com.MiniProject.CourseManager.Service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	
	@Autowired
	public CourseService courseservice;
	
	@PostMapping
	public ResponseEntity<?> addCourses(@RequestBody CourseDetails course){
		try {
		boolean val = courseservice.addCourse(course);
		if(val) {
		   return new ResponseEntity<>(course , HttpStatus.ACCEPTED);
		  }else
		  {
			  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			throw new RuntimeException("Exception is found"+e);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getCourse(){
		try {
			List<CourseDetails> course = courseservice.getCourses();
			if(course != null) {
				return new ResponseEntity<>(course , HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			throw new RuntimeException("Exception"+e);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCourseById(@PathVariable String id) {
	    try {
	        CourseDetails course = courseservice.getById(id);
	        if (course != null) {
	            return new ResponseEntity<>(course, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Error retrieving course by ID: " + e);
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCourseById(@PathVariable String id) {
	    try {
	        boolean deleted = courseservice.deleteCourseById(id);
	        if (deleted) {
	            return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Error deleting course: " + e.getMessage());
	    }
	}
	
}
