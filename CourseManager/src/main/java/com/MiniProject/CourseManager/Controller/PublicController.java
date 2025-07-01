package com.MiniProject.CourseManager.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MiniProject.CourseManager.Entity.User;
import com.MiniProject.CourseManager.Entity.SignUpDetails;
import com.MiniProject.CourseManager.Service.PublicService;

@Controller
@RequestMapping("/public")
public class PublicController {
	
	private String email;
	
	private static final PasswordEncoder passWord  = new BCryptPasswordEncoder();
	
	@Autowired
	private PublicService publicservice;
     
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody  User userdetails) {
		
		boolean isValid  = publicservice.login(userdetails);
		if(isValid) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/create-user")
	public ResponseEntity<?> createUser(@RequestBody  User userdetails){
		userdetails.setPassWord(passWord.encode(userdetails.getPassWord()));
		boolean isAddedToUserDetails = publicservice.insertUser(userdetails);
		boolean isAddToSignUpDetails  = publicservice.insertUserIntoSignUpDetails(userdetails , email);
		if(isAddedToUserDetails  && isAddToSignUpDetails) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUpEntries(@RequestBody SignUpDetails signupdetails){
		
		this.email = signupdetails.getEmail();
		publicservice.sentEmail(email);
		boolean isAdded = publicservice.insertSignUp(signupdetails);
		if(isAdded) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}
	
	@PostMapping("/email-verify/{otp}")
	public ResponseEntity<?> emailVerification(@PathVariable String otp) {
	    boolean isMatch = publicservice.verifyEmailOtp(otp);

	    if (isMatch) {
	        return ResponseEntity.ok("Email verified successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OTP does not match");
	    }
	}
}
	
