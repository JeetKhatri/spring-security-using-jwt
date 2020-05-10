package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.model.User;


/***
 * 
 * @author Jeet Khatri
 * @date 10-May-2020
 *
 */

public interface UserRepository extends JpaRepository<User, Long>{

	/***
	 * This method is used for get user info based on user email address
	 * @param email
	 * @return user object
	 */
	User findByEmail(String email);
	
}
