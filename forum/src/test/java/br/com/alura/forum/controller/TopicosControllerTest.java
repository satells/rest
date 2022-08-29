package br.com.alura.forum.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.forum.base.BaseTest;
import br.com.alura.forum.controller.form.TopicoForm;

class TopicosControllerTest extends BaseTest {

	@Test
	void test_get_withou_parameters() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/topicos");

		mockMvc.perform(requestBuilder)

				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())

				.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))

		;
	}

	@Test
	void test_post() throws Exception {
		TopicoForm topicoForm = new TopicoForm("Dúvida Postman", "Texto da mensagem", "Spring Boot");

		ObjectMapper objectMapper = new ObjectMapper();

		String topico = objectMapper.writeValueAsString(topicoForm);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders

				.post("/topicos")

				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)

				.content(topico)

				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		mockMvc.perform(requestBuilder)

				.andExpect(MockMvcResultMatchers.status().isCreated())

				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

				.andExpect(jsonPath("$", Matchers.notNullValue()))

				.andExpect(jsonPath("$.titulo", is("Dúvida Postman_")))

		;
	}

}
