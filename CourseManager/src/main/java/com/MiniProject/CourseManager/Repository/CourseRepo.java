package com.MiniProject.CourseManager.Repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.MiniProject.CourseManager.Entity.CourseDetails;

@Repository
public interface CourseRepo extends MongoRepository<CourseDetails, String> {
    CourseDetails findBycourseName(String courseName);
    Optional<CourseDetails> findById(String courseName);
}
