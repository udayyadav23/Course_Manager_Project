package com.MiniProject.CourseManager.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.MiniProject.CourseManager.Entity.User;

@Repository
public interface UserRepo extends MongoRepository<User , ObjectId> {

	User findByUserName(String userName);

}
