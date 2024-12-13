package org.sist.sb06_sbb5.user;

import java.util.Optional;

import org.sist.sb06_sbb5.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		
		// 비밀번호 암호화 / 위에 생성함
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword( passwordEncoder.encode(password) );
		
		return this.userRepository.save(user); // 리턴값이 SiteUser 여서
		// return user;
	} //

	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
		if (siteUser.isPresent()) {
			return siteUser.get(); // SiteUser
		} else {
			throw new DataNotFoundException("siteuser not found");
		} // if else
	} //
	
} // class
