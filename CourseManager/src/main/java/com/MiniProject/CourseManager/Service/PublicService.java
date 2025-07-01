package com.MiniProject.CourseManager.Service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.MiniProject.CourseManager.Entity.SignUpDetails;
import com.MiniProject.CourseManager.Entity.User;
import com.MiniProject.CourseManager.Repository.SignUpRepo;
import com.MiniProject.CourseManager.Repository.UserRepo;

@Service
public class PublicService {
    
	private int Otp;
	@Autowired
	private BCryptPasswordEncoder  passWord;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SignUpRepo signuprepo;
	
	public boolean login(User loginRequest) {
		User storedUser = userrepo.findByUserName(loginRequest.getUserName());

	    if (storedUser == null) {
	        return false;
	    }
		if (passWord.matches(loginRequest.getPassWord(), storedUser.getPassWord())) {
	        return true;
	    } else {
	        return false;
	    }
	}

	public boolean insertUser(User userdetails) {
		try {
		    User user = userrepo.save(userdetails);
		    return user != null;
		} catch (Exception e) {
		    return false;
		}
		
	}

	public boolean insertSignUp(SignUpDetails signupdetails) {
		try {
			SignUpDetails user = signuprepo.save(signupdetails);
		    return user != null;
		} catch (Exception e) {
		    return false;
		}
	}

	public boolean insertUserIntoSignUpDetails(User userdetails, String email) {
		try {
		SignUpDetails signUpDetails  = signuprepo.findByEmail(email);
		List<User> OldUserDetails = signUpDetails.getUserDetailsLinkedToMail();
		OldUserDetails.add(userdetails);
		signUpDetails.setUserDetailsLinkedToMail(OldUserDetails);
        SignUpDetails updatedSignUpDetail=signuprepo.save(signUpDetails);
        return updatedSignUpDetail != null;
		}catch(Exception e) {
			return false ; 
		}
	}


	public boolean verifyEmailOtp(String otp) {
		int userOtp = Integer.parseInt(otp);
		System.out.println(Otp +" "+ userOtp);
		if(Otp == userOtp) {
			return true;
		}else
		   return false;
	}

	public void sentEmail(String email) {
		Random random = new Random();
		int otp = 100000 + random.nextInt(999999);
		this.Otp = otp ;
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom("22r11a05m9@gcet.edu.in");
		simpleMessage.setTo(email);
		simpleMessage.setSubject("otp verification for courseManager");
		simpleMessage.setText("Your Otp is "+otp);	
		try {
		   mailSender.send(simpleMessage);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
