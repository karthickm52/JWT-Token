package jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jwt.common.APIResponse;
import jwt.dto.LoginRequestDTO;
import jwt.dto.SignupRequestDTO;
import jwt.service.LoginService;




@Controller
public class LoginController {
	
	@Autowired
	public LoginService loginService;

	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignupRequestDTO signupRequestDTO)
	{
		APIResponse apiResponse = loginService.signup(signupRequestDTO);
		
		return ResponseEntity.
				status(apiResponse.getStatus())
				.body(apiResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO)
	{
		APIResponse apiResponse = loginService.login(loginRequestDTO);
		
		return ResponseEntity.
				status(apiResponse.getStatus())
				.body(apiResponse);
	}
	
}
