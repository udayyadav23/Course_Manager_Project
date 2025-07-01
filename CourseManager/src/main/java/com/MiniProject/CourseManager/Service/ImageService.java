package com.MiniProject.CourseManager.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MiniProject.CourseManager.Entity.CourseDetails;
import com.MiniProject.CourseManager.Entity.ImageData;
import com.MiniProject.CourseManager.Repository.CourseRepo;
import com.MiniProject.CourseManager.Repository.ImageRepo;

import io.jsonwebtoken.io.IOException;

@Service
public class ImageService {
    
	@Autowired
	private ImageRepo imagerepo;
	
	@Autowired
    private CourseRepo courserepo;
	
	public String saveImage(MultipartFile file) throws IOException, java.io.IOException {
        ImageData image = new ImageData(
                null,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes()
        );
        imagerepo.save(image);
        return image.getId();
    }
	

    public List<ImageData> getAllImages() {
        return imagerepo.findAll();
    }


	public ImageData getById(String id) {
		return imagerepo.findById(id).orElse(null);
	}
	
   public boolean addImageToAssignments(String id , String course) {
	   try {
		   CourseDetails cou = courserepo.findBycourseName(course);
		   List<String> str = cou.getAssignments();
		   str.add(id);
		   cou.setAssignments(str);
		   CourseDetails coursedet = courserepo.save(cou);
		   if(coursedet != null) {
			   return true;
		   }else {
			   return false;
		   }
	   }catch(Exception e) {
		   throw new RuntimeException("Exception found"+e);
	   }
   }
   
   public boolean addImageToProjects(String id , String course) {
	   try {
		   CourseDetails cou = courserepo.findBycourseName(course);
		   List<String> str = cou.getProjects();
		   str.add(id);
		   cou.setProjects(str);
		   CourseDetails coursedet = courserepo.save(cou);
		   if(coursedet != null) {
			   return true;
		   }else {
			   return false;
		   }
	   }catch(Exception e) {
		   throw new RuntimeException("Exception found"+e);
	   }
   }
   
   
   public boolean addImageToExtras(String id , String course) {
	   try {
		   CourseDetails cou = courserepo.findBycourseName(course);
		   List<String> str = cou.getExtras();
		   str.add(id);
		   cou.setExtras(str);
		   CourseDetails coursedet = courserepo.save(cou);
		   if(coursedet != null) {
			   return true;
		   }else {
			   return false;
		   }
	   }catch(Exception e) {
		   throw new RuntimeException("Exception found"+e);
	   }
   }
   
   public boolean addImageToNotes(String id , String course) {
	   try {
		   CourseDetails cou = courserepo.findBycourseName(course);
		   List<String> str = cou.getNotes();
		   str.add(id);
		   cou.setNotes(str);
		   CourseDetails coursedet = courserepo.save(cou);
		   if(coursedet != null) {
			   return true;
		   }else {
			   return false;
		   }
	   }catch(Exception e) {
		   throw new RuntimeException("Exception found"+e);
	   }
   }
}
