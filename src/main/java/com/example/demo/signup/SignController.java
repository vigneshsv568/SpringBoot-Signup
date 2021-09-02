package com.example.demo.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

	@Autowired
	private SignService signService;

	@PostMapping("/signup")
	public ResponseEntity<JSONData> create(@RequestBody SignPojo signPojo) {

		signService.create(signPojo);
		if (signPojo.getMsg() != null && !signPojo.getMsg().isEmpty()) {

			JSONData json = new JSONData();
			json.setMsg(signPojo.getMsg());
			json.setEmail(signPojo.getEmail());
			return new ResponseEntity<>(json, HttpStatus.OK);
		} else {
			JSONData json = new JSONData();
			json.setMsg(signPojo.getMsg());
			json.setEmail(signPojo.getEmail());
			return new ResponseEntity<>(json, HttpStatus.OK);
		}
	}

}
