package com.insper.iotserver.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.insper.iotserver.model.State;
import com.insper.iotserver.repository.DeviceRepository;
import com.insper.iotserver.repository.StateRepository;

@RestController
@CrossOrigin
@RequestMapping("/state")
public class StateController {

	@Autowired
	private DeviceRepository deviceRepo;

	@Autowired
	private StateRepository repo;

	@GetMapping
	public List<State> get() {
		return repo.findAll();
	}

	@GetMapping("/last")
	public State getLast() {
		List<State> list = repo.findAll();
		return list.get(list.size() - 1);
	}

	@GetMapping("/{id}/last")
	public List<State> getLastItem(@PathVariable Long id) {
		List<State> lastItem = repo.getLastByDeviceId(id);
		return lastItem;
	}

	@PostMapping
	public ResponseEntity<State> post(@RequestBody State obj, HttpServletResponse response) {
		obj.setDesiredDateTime(LocalDateTime.now());
		if (deviceRepo.findById(obj.getDevice().getId()) != null) {
			State objSaved = repo.save(obj);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(objSaved.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());

			return ResponseEntity.status(HttpStatus.CREATED).body(objSaved);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PutMapping  // Designed to Front-End
	public ResponseEntity<State> updateState(@RequestBody State obj,
			HttpServletResponse response) {

		State dbObj = repo.getOne(obj.getId());
		if ((deviceRepo.findById(obj.getDevice().getId()) != null)
				&& (dbObj != null) && (obj != null)) {

			obj.setDesiredDateTime(LocalDateTime.now());
			State objSaved = repo.save(copyObj(obj, dbObj));

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(objSaved.getId())
					.toUri();
			response.setHeader("Location", uri.toASCIIString());

			return ResponseEntity.status(HttpStatus.OK).body(objSaved);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PutMapping("/last")  // Designed to Embbeded Systems
	public ResponseEntity<State> updateLastState(@RequestBody State obj,
			HttpServletResponse response) {

		List<State> list = repo.getLastByDeviceId(obj.getDevice().getId());
		State dbObj = list.get(0);
		if ((deviceRepo.findById(obj.getDevice().getId()) != null)
				&& (dbObj != null) && (obj != null)) {

			obj.setDateTime(LocalDateTime.now());
			State objSaved = repo.save(copyObj(obj, dbObj));

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(objSaved.getId())
					.toUri();
			response.setHeader("Location", uri.toASCIIString());

			return ResponseEntity.status(HttpStatus.OK).body(objSaved);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	

	private State copyObj(State obj, State dbObj) {
		if (obj.getState() != null) {
			dbObj.setState(obj.getState());
		}

		if (obj.getMsgCode() != null) {
			dbObj.setMsgCode(obj.getMsgCode());
		}

		if (obj.getDateTime() != null) {
			dbObj.setDateTime(obj.getDateTime());
		}

		if (obj.getDesiredState() != null) {
			dbObj.setDesiredState(obj.getDesiredState());
		}

		if (obj.getDesiredDateTime() != null) {
			dbObj.setDesiredDateTime(obj.getDesiredDateTime());
		}

		return dbObj;
	}
}
