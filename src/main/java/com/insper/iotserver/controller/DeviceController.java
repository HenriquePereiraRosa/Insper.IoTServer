package com.insper.iotserver.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.insper.iotserver.model.Device;
import com.insper.iotserver.repository.DeviceRepository;

@RestController
@CrossOrigin
@RequestMapping("/device")
public class DeviceController {

	@Autowired
	private DeviceRepository repo;

	@GetMapping
	public List<Device> get() {
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Device> get(@PathVariable Long id) {
		return repo.findById(id);
	}

	@PostMapping
	public ResponseEntity<Device> post(@RequestBody Device obj, HttpServletResponse response) {
		Device objtSaved = repo.save(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(objtSaved.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.status(HttpStatus.CREATED).body(objtSaved);
	}
}
