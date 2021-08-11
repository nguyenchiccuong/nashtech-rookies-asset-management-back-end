package com.nashtech.rootkies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nashtech.rootkies.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Optional<User> findByStaffCode(String staffcode);

	//Boolean existsByEmail(String email);
}
