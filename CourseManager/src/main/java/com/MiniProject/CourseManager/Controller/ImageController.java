package com.MiniProject.CourseManager.Controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.MiniProject.CourseManager.Entity.ImageData;
import com.MiniProject.CourseManager.Service.ImageService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/uploads")
public class ImageController {
	
	
	@Autowired
	private ImageService imageservice;
	
	@PostMapping("/assignments/{course}")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file , @PathVariable String course) throws IOException, java.io.IOException{
		String id = imageservice.saveImage(file);
		boolean val = imageservice.addImageToAssignments(id, course);
		try {
			if(id != null && val == true) {
				return new ResponseEntity<>(id ,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(id ,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(id ,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/projects/{course}")
	public ResponseEntity<?> uploadImageToProjects(@RequestParam("file") MultipartFile file , @PathVariable String course) throws IOException, java.io.IOException{

		String id = imageservice.saveImage(file);
		boolean val = imageservice.addImageToProjects(id, course);
		try {
			if(id != null && val == true) {
				return new ResponseEntity<>(id ,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(id ,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(id ,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/notes/{course}")
	public ResponseEntity<?> uploadImageToNotes(@RequestParam("file") MultipartFile file , @PathVariable String course) throws IOException, java.io.IOException{
		
		String id = imageservice.saveImage(file);
		boolean val = imageservice.addImageToNotes(id, course);
		try {
			if(id != null && val == true) {
				return new ResponseEntity<>(id ,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(id ,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(id ,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/extras/{course}")
	public ResponseEntity<?> uploadImageToExtras(@RequestParam("file") MultipartFile file , @PathVariable String course) throws IOException, java.io.IOException{
		
		String id = imageservice.saveImage(file);
		boolean val = imageservice.addImageToExtras(id, course);
		try {
			if(id != null && val == true) {
				return new ResponseEntity<>(id ,HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>(id ,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(id ,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping()
	public List<ImageData> getImages(){
		return imageservice.getAllImages().stream()
				.map(img -> new ImageData(img.getId() , img.getImageName() , img.getType()))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable String id) {
        ImageData image = imageservice.getById(id);

        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getImageName() + "\"")
                    .body(image.getImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
