package com.shopit.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopit.admin.user.repository.UserRepository;
import com.shopit.common.entity.Role;
import com.shopit.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TestEntityManager entityManager;

	/* CREATE OPERATION */
	/* Create the user */
	@Test
	public void createUserTest() {

		Role roleShre = entityManager.find(Role.class, 1);
		User userShre = new User("shre.learning@gmail.com", "shr2025", "Shreyas", "G");
		userShre.addRole(roleShre);
		User savedUser = userRepo.save(userShre);
		assertThat(savedUser.getId()).isGreaterThan(0);

	}

	/* Create the user with multiple roles */
	@Test
	public void createUsersWithTwoRoles() {
		User userKhushi = new User("khushisrinivas123@gmail.com", "KS219", "Khushi", "Srinivas");
		Role editorRole = new Role(3);
		Role assistantRole = new Role(5);
		userKhushi.addRole(editorRole);
		userKhushi.addRole(assistantRole);

		User savedUser = userRepo.save(userKhushi);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	/* READ OPERATION */
	/* List all the users */

	@Test
	public void listAllUsersTest() {
		Iterable<User> allUsers = userRepo.findAll();
		allUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void getUserById() {
		User user = userRepo.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
	}

	/* UPDATE OPERATION */

	@Test
	public void updateUserDetailsTest() {
		User user = userRepo.findById(2).get();
		user.setEnabled(true);
		user.setEmail("ks219@gmail.com");

		userRepo.save(user);

	}
	
	@Test
	public void updateUserRole() {
		User user = userRepo.findById(2).get();
		Role editorRole = new Role(3);
		Role salesPersonRole = new Role(2);
		user.getRoles().remove(editorRole);
		user.addRole(salesPersonRole);
		userRepo.save(user);

	}

	/* DELETE OPERATION */
	@Test
	public void deleteUserTest() {
		userRepo.deleteById(2);
	}
	
	/* CHECK FOR UNIQUE EMAIL */
	
	@Test
	public void checkUniqueEmail() {
		String email = "bruno@outlook.com";
		User userByEmail = userRepo.getUserByEmail(email);
		assertThat(userByEmail).isNotNull();
	}
	
	@Test
	public void testCountByID() {
		Integer id = 100;
		Long countByID = userRepo.countById(id);
		assertThat(countByID).isNotNull().isGreaterThan(0);
	}
		

}
