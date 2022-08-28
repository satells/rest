package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@RestController
public class TopicosController {

	@RequestMapping("/topicos")
	public List<TopicoDto> lista() {

		Topico topico1 = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "programacao"));
		Topico topico2 = new Topico("Dúvida", "Dúvida com Java", new Curso("Java", "programacao"));

		return TopicoDto.converter(Arrays.asList(topico1, topico1, topico1, topico2));

	}

}
