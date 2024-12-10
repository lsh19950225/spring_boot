package org.sist.sb06_sbb5.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		
		// 비밀번호 암호화
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword( passwordEncoder.encode(password) );
		
		this.userRepository.save(user);
		return user;
	} //
	
} // class
