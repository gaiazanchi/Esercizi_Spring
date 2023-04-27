package co.develhope.testController;

import co.develhope.testController.controller.UserController;
import co.develhope.testController.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class UserTests {

	@Autowired
	UserController controller;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	private User createAUser() throws Exception{
		User user = new User();
		user.setName("Gaia");
		user.setSurname("Zanchi");

		return createAUser(user);
	}

	private User createAUser(User user) throws Exception{
		MvcResult result = createAUserRequest(user);
		User userFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
		return userFromResponse;

	}

	private MvcResult createAUserRequest() throws Exception{
		User user = new User();
		user.setName("Gaia");
		user.setSurname("Zanchi");

		return createAUserRequest(user);
	}

	private MvcResult createAUserRequest(User user) throws Exception{
		if(user == null) return null;
		String userJson = objectMapper.writeValueAsString(user);

		return this.mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson)).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

	private User getUserFromId(Integer id) throws Exception{
		MvcResult result = this.mockMvc.perform(get("/users/" + id))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();

		try{
			String userJson = result.getResponse().getContentAsString();
			return objectMapper.readValue(userJson, User.class);
		}catch(Exception e ){
			return null;
		}
	}

	@Test
	void createAUserTest() throws Exception {
		User studentFromResponse = createAUser();
		assertThat(studentFromResponse.getId()).isNotNull();
	}

	@Test
	void readUserList() throws Exception {
		createAUserRequest();
		createAUserRequest();
		createAUserRequest();

		MvcResult result = this.mockMvc.perform(get("/users")).
				andDo(print()).andExpect(status().isOk()).andReturn();

		List<User> usersFromResponse = objectMapper.readValue
				(result.getResponse().getContentAsString(), List.class);

		assertThat(usersFromResponse.size()).isNotZero();
	}

	@Test
	void readSingleUser() throws Exception{
		User user = createAUser();
		assertThat(user.getId()).isNotNull();

		User userFromResponse = getUserFromId(user.getId());
		assertThat(userFromResponse).isNotNull();
		assertThat(userFromResponse.getId()).isEqualTo(user.getId());
	}

	@Test
	void updateSingleUser() throws Exception{
		User user = createAUser();
		assertThat(user.getId()).isNotNull();

		user.setName("Maria");

		String userJson = objectMapper.writeValueAsString(user);

		MvcResult result = this.mockMvc.perform(put("/student/" + user.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		User userFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
		assertThat(userFromResponse).isNotNull();
		assertThat(userFromResponse.getId()).isEqualTo(user.getId());
		assertThat(userFromResponse.getName()).isEqualTo(user.getName());

	}

	@Test
	void deleteSingleUser()throws Exception{
		User user = createAUser();
		assertThat(user.getId()).isNotNull();

		this.mockMvc.perform(delete("/student/" + user.getId()))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();

		User userFromResponseGet = getUserFromId(user.getId());
		assertThat(userFromResponseGet).isNull();
	}

}
