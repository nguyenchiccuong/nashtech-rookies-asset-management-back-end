package com.nashtech.rootkies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nashtech.rootkies.model.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> , JpaSpecificationExecutor<User> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Optional<User> findByStaffCode(String staffcode);

	//Boolean existsByEmail(String email);
	@Query(value = "SELECT CASE  WHEN count(staffcode) > 0 THEN true ELSE false END checkExist FROM "+
			"users u  where staffcode =?1 and isdeleted = false" , nativeQuery = true)
	Boolean checkUserExist(String staffCode);

	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET isdeleted = true WHERE staffcode =?1" , nativeQuery = true)
	void disableUser(String staffCode);
}
