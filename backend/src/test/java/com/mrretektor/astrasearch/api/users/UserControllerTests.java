package com.mrretektor.astrasearch.api.users;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mrretektor.astrasearch.TestDataUtil;
import com.mrretektor.astrasearch.dao.UserDao;
import com.mrretektor.astrasearch.domain.User;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {
	    UserController.class,
	})
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private UserDao usersDao;
	
	
	private String path = "/api/users/";
	
	
	@Test
	public void testThatUsersControllerReturnsUser() throws Exception {
		User user = TestDataUtil.createTestUser();
		String username = user.getUsername();
		
		when(usersDao.findOne(username)).thenReturn(Optional.of(user));
		
		mockMvc.perform(get(path + username))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(user.getId()))
				.andExpect(jsonPath("$.username").value(username))
				.andExpect(jsonPath("$.firstName").value(user.getFirstName()))
				.andExpect(jsonPath("$.lastName").value(user.getLastName()))
				.andExpect(jsonPath("$.email").value(user.getEmail()))
				;
	}
	
	@Test
	public void testThatUsersControllerReturns404() throws Exception {
		
		when(usersDao.findOne("")).thenReturn(Optional.empty());
		
		mockMvc.perform(get(path))
		.andExpect(status().isNotFound());
	}
}
