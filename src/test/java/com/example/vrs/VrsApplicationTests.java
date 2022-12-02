package com.example.vrs;

import com.example.vrs.model.repository.DestinationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudentTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private DestinationRepository destinationRepository;

	// @Test
	// void getStudent() throws Exception{
	// Student s = studentRepository.findById(1111L).orElseThrow(() -> new
	// StudentNotFoundException(1111L));

	// ObjectNode expectedJson = objectMapper.createObjectNode();
	// expectedJson.put("firstName", "Tyrion");
	// expectedJson.put("lastName", "Lannister");
	// mockMvc.perform(get("/students/1111"))
	// .andDo(print())
	// .andExpect(status().is2xxSuccessful())
	// .andExpect(content().json(expectedJson.toString()));
	// }

}
