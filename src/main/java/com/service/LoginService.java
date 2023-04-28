
 

package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.Login;
import com.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	LoginRepository loginRepository;
	
	
	public String signUp(Login login) {
		System.out.println("Email ID before saving: " + login.getEmailid());
		if(login.getTypeofuser().equalsIgnoreCase("admin")) {
			return "Admin account can't create";
		}else {
			Optional<Login> result = loginRepository.findById(login.getEmailid());
			if(result.isPresent()) {
				return "Already account present";
			}else {
				loginRepository.save(login);
				return "Account created successfully";
			}
		}
	}
	

    public List<Login> findAllUsers() {
        return loginRepository.findAll();
    }
    public List<Login> searchUsers(String searchTerm) {
        return loginRepository.searchUsersByEmail(searchTerm);
    }
	
	public String signIn(Login login) {
		Login ll = loginRepository.signIn(login.getEmailid(), login.getPassword(), login.getTypeofuser());
		if(ll==null) {
			return "Invalid emailid or password";
		}else {
			if(ll.getTypeofuser().equalsIgnoreCase("admin")) {
				return "Admin login successfully";
			}else {
				return "Customer login successfully";
			}
		}
		
	}
	
	   public boolean isCurrentPasswordCorrect(String emailid, String currentPassword) {
	        Login user = findLoginByEmail(emailid);
	        if (user != null) {
	            return user.getPassword().equals(currentPassword);
	        }
	        return false;
	    }
	
	 public Login findLoginByEmail(String emailid) {
	        Optional<Login> result = loginRepository.findById(emailid);
	        return result.orElse(null);
	    }
	 
	 public void updatePassword(String emailid, String newPassword) {
	        Login user = loginRepository.findById(emailid).orElse(null);

	        if (user != null) {
	            user.setPassword(newPassword);
	            loginRepository.save(user);
	        }
	    }
}