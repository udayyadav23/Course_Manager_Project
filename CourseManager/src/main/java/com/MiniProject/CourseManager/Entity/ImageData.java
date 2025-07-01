package com.MiniProject.CourseManager.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Image")
public class ImageData {
	
	@Id
	private String id;
	
	private String imageName;
	
	private String type;
	
	@Indexed(unique = true)
	private byte[] image;
	
	public ImageData() {
		
	}
	public ImageData(String id , String imageName , String type , byte[] image) {
			this.id = id;
			this.imageName = imageName;
			this.type  = type;
			this.image = image;
	}
	

	public ImageData(String id2, String imageName2, String type2) {
		this.id = id2 ;
		this.imageName = imageName2;
		this.type = type2;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
