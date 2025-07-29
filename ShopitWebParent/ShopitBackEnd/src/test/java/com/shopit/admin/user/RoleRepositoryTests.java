package com.shopit.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;

import com.shopit.admin.user.repository.RoleRepository;
import com.shopit.common.entity.Role;


/*| Annotation                    | Purpose                                                             |
| ----------------------------- | ------------------------------------------------------------------- |
| `@SpringBootTest`             | Starts full Spring Boot app context for integration testing         |
| `@EnableJpaRepositories(...)` | Tells Spring where to find repository interfaces                    |
| `@EntityScan(...)`            | Tells Spring where to find `@Entity` classes                        |
| `@Rollback(false)`            | Keeps DB changes made during the test (no rollback after test ends) |
| `@Autowired`                  | Injects the `RoleRepository` to interact with the DB                |*/


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@EnableJpaRepositories(basePackages = "com.shopit.admin.user")
@EntityScan(basePackages = "com.shopit.common.entity")
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository roleRepo;

	@Test
	public void testCreateFirstRole() {

		Role roleAdmin = new Role("Admin", "Manages Everything");
		Role savedRole = roleRepo.save(roleAdmin);
		assertThat(savedRole.getID()).isGreaterThan(0);
	}

	@Test
	public void testCreateRestRoles() {

		Role roleSalesPerson = new Role("Salesperson",
				"Manage product price, customers,shipping orders and sales report");
		Role roleEditor = new Role("Editor",
				"Manage categories,brands,products,artickes and menus");
		Role roleShipper = new Role("Shipper",
				"View Products, view orders and update order status");
		Role roleAssistant = new Role("Assistant",
				"Manage questions and reviews");
		roleRepo.saveAll(List.of(roleSalesPerson,roleEditor,roleShipper,roleAssistant));

	}

}
