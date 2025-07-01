package com.MiniProject.CourseManager.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.MiniProject.CourseManager.Entity.ImageData;

public interface ImageRepo extends MongoRepository<ImageData, String>{

}
