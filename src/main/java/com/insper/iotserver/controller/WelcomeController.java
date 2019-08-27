package com.insper.iotserver.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class WelcomeController {
	
	@Autowired
	Environment environment;


	@GetMapping
	public List<String> get() {
		List<String> list = new ArrayList<>();
		list.add("Welcome to INSPER IoT Server");
		list.add("<a href:\"https://insper.edu.br\">Insper<a>" );
		list.add("Please check out at insper.edu.br");
		list.add("Today is: " + LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
		list.add("Server is running on PORT: " + 
				environment.getProperty("local.server.port") + "...");
		return list;
	}
}
