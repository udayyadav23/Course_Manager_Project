package com.MiniProject.CourseManager.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.MiniProject.CourseManager.Entity.SignUpDetails;

@Repository
public interface SignUpRepo extends MongoRepository<SignUpDetails, ObjectId>{

	SignUpDetails findByEmail(String email);
	

}
