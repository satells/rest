package br.com.alura.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.alura.forum.base.BaseTest;

class TopicosControllerTest extends BaseTest {

	@Test
	void test() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/topicos")

		)

				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())

				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))

		;

	}

}
