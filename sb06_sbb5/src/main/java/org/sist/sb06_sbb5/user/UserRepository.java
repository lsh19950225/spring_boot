package org.sist.sb06_sbb5.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	
	// read()
	Optional<SiteUser> findByUsername(String username);
	// Optional<SiteUser> findByusername(String username);
	
} // interface
