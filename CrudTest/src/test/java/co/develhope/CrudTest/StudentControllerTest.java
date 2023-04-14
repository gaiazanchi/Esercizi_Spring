package co.develhope.CrudTest;

import co.develhope.CrudTest.controllers.StudentController;
import co.develhope.CrudTest.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class StudentControllerTest {

	@Autowired
	private StudentController studentController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void userControllerLoads() {
		assertThat(studentController).isNotNull();
	}
	private Student createAStudent() throws Exception{
		Student student = new Student();
		student.setWorking(true);
		student.setName("Gaia");
		student.setSurname("Zanchi");

		return createAStudent(student);
	}

	private Student createAStudent(Student student) throws Exception{
		MvcResult result = createAStudentRequest(student);
		Student studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		return studentFromResponse;

	}

	private MvcResult createAStudentRequest() throws Exception{
		Student student = new Student();
		student.setWorking(true);
		student.setName("Gaia");
		student.setSurname("Zanchi");

		return createAStudentRequest(student);
	}

	private MvcResult createAStudentRequest(Student student) throws Exception{
		if(student == null) return null;
		String studentJson = objectMapper.writeValueAsString(student);

		return this.mockMvc.perform(post("/student")
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJson)).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

	private Student getUserFromId(Integer id) throws Exception{
		MvcResult result = this.mockMvc.perform(get("/student/" + id))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();

		try{
			String studentJson = result.getResponse().getContentAsString();
			return objectMapper.readValue(studentJson, Student.class);
		}catch(Exception e ){
			return null;
		}
	}

	@Test
	void createAUserTest() throws Exception {
		Student studentFromResponse = createAStudent();
		assertThat(studentFromResponse.getId()).isNotNull();
	}

	@Test
	void readUserList() throws Exception {
		createAStudentRequest();
		createAStudentRequest();
		createAStudentRequest();

		MvcResult result = this.mockMvc.perform(get("/student")).
				andDo(print()).andExpect(status().isOk()).andReturn();

		List<Student> studentsFromResponse = objectMapper.readValue
				(result.getResponse().getContentAsString(), List.class);

		assertThat(studentsFromResponse.size()).isNotZero();
	}

	@Test
	void readSingleUser() throws Exception{
		Student student = createAStudent();
		assertThat(student.getId()).isNotNull();

		Student studentFromResponse = getUserFromId(student.getId());
		assertThat(studentFromResponse).isNotNull();
		assertThat(studentFromResponse.getId()).isEqualTo(student.getId());
	}

	@Test
	void updateSingleUser() throws Exception{
		Student student = createAStudent();
		assertThat(student.getId()).isNotNull();

		student.setName("Maria");

		String studentJson = objectMapper.writeValueAsString(student);

		MvcResult result = this.mockMvc.perform(put("/student/" + student.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJson)).andDo(print())
				.andExpect(status().isOk()).andReturn();

		Student studentFromResponse = objectMapper.readValue
				(result.getResponse().getContentAsString(), Student.class);
		assertThat(studentFromResponse).isNotNull();
		assertThat(studentFromResponse.getId()).isEqualTo(student.getId());
		assertThat(studentFromResponse.getName()).isEqualTo(student.getName());

	}

	@Test
	void deleteSingleUser()throws Exception{
		Student student = createAStudent();
		assertThat(student.getId()).isNotNull();

		this.mockMvc.perform(delete("/student/" + student.getId()))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();

		Student studentFromResponseGet = getUserFromId(student.getId());
		assertThat(studentFromResponseGet).isNull();
	}

	@Test
	void workingSingleUser()throws Exception{
		Student student = createAStudent();
		assertThat(student.getId()).isNotNull();

		MvcResult result = this.mockMvc.perform(put("/student/" + student.getId() + "/working?working=true"))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();
		Student studentFromResponse = objectMapper.readValue
				(result.getResponse().getContentAsString(), Student.class);
		assertThat(studentFromResponse).isNotNull();
		assertThat(studentFromResponse.getId()).isEqualTo(student.getId());
		assertThat(studentFromResponse.isWorking()).isEqualTo(true);

		Student studentFromResponseGet = getUserFromId(student.getId());
		assertThat(studentFromResponseGet).isNotNull();
		assertThat(studentFromResponseGet.getId()).isEqualTo(student.getId());
		assertThat(studentFromResponseGet.isWorking()).isEqualTo(true);
	}

}
