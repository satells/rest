package br.com.alura.forum.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
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

				.andExpect(status().is2xxSuccessful())

				.andExpect(content().contentType("application/json;charset=UTF-8"))

		;
	}

	@Test
	void test_post() throws Exception {

		String topico = new ObjectMapper().writeValueAsString(new TopicoForm("Dúvida Postman", "Texto da mensagem", "Spring Boot"));

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders

				.post("/topicos")

				.accept(APPLICATION_JSON_UTF8_VALUE)

				.content(topico)

				.contentType(APPLICATION_JSON_UTF8_VALUE);

		mockMvc.perform(requestBuilder)

				.andExpect(status().isCreated())

				.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))

				.andExpect(MockMvcResultMatchers.redirectedUrlPattern("http://**/topicos/*"))

				.andExpect(jsonPath("$", notNullValue()))

				.andExpect(jsonPath("$.id", notNullValue()))

				.andExpect(jsonPath("$.titulo", is("Dúvida Postman")))

				.andExpect(jsonPath("$.mensagem", is("Texto da mensagem")))

				.andExpect(jsonPath("$.dataCriacao", notNullValue()));

	}

	@Test
	void test_post_with_error_on_validation() throws Exception {
		String topico = new ObjectMapper().writeValueAsString(new TopicoForm("", "Texto da mensagem", "Spring Boot"));

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders

				.post("/topicos")

				.accept(APPLICATION_JSON_UTF8_VALUE)

				.header("Accept-Language", "pt-BR")

				.content(topico)

				.contentType(APPLICATION_JSON_UTF8_VALUE);

		;

		mockMvc.perform(requestBuilder)

				.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))

				.andExpect(status().is4xxClientError())

				.andExpect(jsonPath("$", notNullValue()))

				.andExpect(jsonPath("$[0].campo", is("titulo")))

				.andExpect(jsonPath("$[0].erro", is("Mínimo de 5 caracteres")))

		;

	}

}
