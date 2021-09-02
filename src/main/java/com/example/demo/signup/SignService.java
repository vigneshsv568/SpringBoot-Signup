package com.example.demo.signup;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignService {

	@Autowired
	private SignDao signDao;

	public SignPojo create(SignPojo signPojo) {
		String regex = "^(?=.*[0-9])"
		        + "(?=.*[a-z])(?=.*[A-Z])"
		        + "(?=.*[@#$%^&+=])"
		        + "(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex);
		String regex1 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		 
		Pattern pattern = Pattern.compile(regex1);
		Matcher matcher = pattern.matcher(signPojo.getEmail());
		Matcher m = p.matcher(signPojo.getPassword());

		List<SignEntity> emial = signDao.check(signPojo.getEmail());
		if (emial != null && !emial.isEmpty()) {
			signPojo.setMsg("Already registered");

		} else if(m.matches() == false) {
			signPojo.setMsg("Please use Password Format");
			
		}else if(matcher.matches() == false) {
			signPojo.setMsg("Please check email");
		}
		else{

			SignEntity obj = new SignEntity();

			obj.setFname(signPojo.getFname());
			obj.setLname(signPojo.getLname());

			obj.setEmail(signPojo.getEmail());
			obj.setPhone(signPojo.getPhone());
			obj.setPassword(signPojo.getPassword());
			signDao.save(obj);
			signPojo.setMsg("Account Created");
		}

		return signPojo;
	}
}


/*
String regex = "^(?=.*[0-9])"
        + "(?=.*[a-z])(?=.*[A-Z])"
        + "(?=.*[@#$%^&+=])"
        + "(?=\\S+$).{8,20}$";
String regex1 = "^[A-Za-z]\\w{5,29}$";
Pattern p = Pattern.compile(regex);
Pattern user = Pattern.compile(regex1);

Matcher m = p.matcher(login.getPassword());
Matcher m1 = user.matcher(login.getUsername());

if ((m1.matches()==false || m.matches()==false)
		&& (login.getPassword() == null || login.getPassword().isEmpty())) {
	return "Please enter Username and Password";
} else if (m.matches() == false) {
	return "Please enter First letter Capital folowed by small & special character and numbers";
}else if (m1.matches() == false) {
		return "Please enter only characters 8-16 letters";
}else if (login.getUsername() == null || login.getUsername().isEmpty()) {
	return "Please enter Username";
} else if (login.getPassword() == null || login.getPassword().isEmpty()) {
	return "Please enter Password";
} else {
	return "Login Success";
}
*/