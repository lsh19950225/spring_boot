package org.sist.sb06_sbb5.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

// 회원 엔티티
@Entity
@Getter
@Setter
public class SiteUser { // site_user
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@Column(unique = true)
	private String email;
	
} // class
