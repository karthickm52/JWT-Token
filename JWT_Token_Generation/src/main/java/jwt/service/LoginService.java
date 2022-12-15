package jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwt.common.APIResponse;
import jwt.dto.LoginRequestDTO;
import jwt.dto.SignupRequestDTO;
import jwt.model.User;
import jwt.repository.UserRepository;
import jwt.utils.JwtUtil;





@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JwtUtil jwtUtil;

	public APIResponse signup(SignupRequestDTO signupRequestDTO) {
		
		APIResponse apiResponse = new APIResponse();
		
//		apiResponse.setData("its working");
		
//		validation
		
//		DTO to entity
		
		User userEntity = new User();
		
		userEntity.setName(signupRequestDTO.getName());
		userEntity.setEmail(signupRequestDTO.getEmail());
		userEntity.setIsActive(Boolean.TRUE);
		userEntity.setGender(signupRequestDTO.getGender());
		userEntity.setPassword(signupRequestDTO.getPassword());
		userEntity.setPhone(signupRequestDTO.getPhone());
		
//		store Entity
		
		userEntity = userRepo.save(userEntity);
		
//		return
		
//		apiResponse.setData("users created successfully");
		apiResponse.setData(userEntity);
		
		return apiResponse;
	}

	public APIResponse login(LoginRequestDTO loginRequestDTO) {

		APIResponse apiResponse = new APIResponse();


		// verify user exist with given email and password

		List<User> u=(List<User>) userRepo.findAll();

		System.out.println("Coming Email "+loginRequestDTO.getEmail());
		for(User u1:u)
		{
			System.out.println("U1"+u1.getEmail());
			if(u1.getEmail().equals(loginRequestDTO.getEmail()))
			{
				if(u1.getPassword().equals(loginRequestDTO.getPassword()))
				{
					System.out.println("The user will be finded ");
					
					String token = jwtUtil.generateJwt(u1);
					
					apiResponse.setData(token);
					
					return apiResponse;
				}
				else
				{
					apiResponse.setData("Oops, email & password does not matched.");
					
					return apiResponse;
				}

			}
		}
		apiResponse.setData("This "+loginRequestDTO.getEmail()+" - mail not found.");
		return apiResponse;
	}

}
