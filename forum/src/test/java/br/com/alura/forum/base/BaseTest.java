package br.com.alura.forum.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.forum.ForumApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { ForumApplication.class })
@AutoConfigureMockMvc
public class BaseTest {
	@Autowired
	protected MockMvc mockMvc;

}
