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

import com.insper.iotserver.model.Color;
import com.insper.iotserver.repository.ColorRepository;
import com.insper.iotserver.repository.DeviceRepository;

@RestController
@CrossOrigin
@RequestMapping("/colors")
public class ColorController {

	@Autowired
	private DeviceRepository deviceRepo;

	@Autowired
	private ColorRepository repo;

	@GetMapping
	public List<Color> get() {
		return repo.findAll();
	}

	@GetMapping("/last")
	public Color getLast() {
		List<Color> list = repo.findAll();
		return list.get(list.size() - 1);
	}

	@GetMapping("/{id}/last")
	public List<Color> getLastItem(@PathVariable Long id) {
		List<Color> lastItem = repo.getLastByDeviceId(id);
		return lastItem;
	}

	@PostMapping
	public ResponseEntity<Color> post(@RequestBody Color obj, HttpServletResponse response) {
		obj.setDesiredDateTime(LocalDateTime.now());
		if (deviceRepo.findById(obj.getDevice().getId()) != null) {
			Color objSaved = repo.save(obj);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(objSaved.getId())
					.toUri();
			response.setHeader("Location", uri.toASCIIString());

			return ResponseEntity.status(HttpStatus.CREATED).body(objSaved);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	

	/**
	 * Returns an RenponseEntity object that is the result of an  
	 *  object updated in DB.
	 * This method always returns immediately, whether or not the 
	 * image exists. When this applet attempts to draw the image on
	 * the screen, the data will be loaded. The graphics primitives 
	 * that draw the image will incrementally paint on the screen. 
	 *
	 * @param  @RequestBody Color object with the attributes to update.
	 * @return      The updated object
	 * @see         ...
	 */
	@PutMapping  // Designed to Embbeded Systems
	public ResponseEntity<Color> updateColor(@RequestBody Color obj,
			HttpServletResponse response) {

		Color dbObj = repo.getOne(obj.getId());
		if ((deviceRepo.findById(obj.getDevice().getId()) != null)
				&& (dbObj != null) && (obj != null)) {

			obj.setDesiredDateTime(LocalDateTime.now());
			Color objSaved = repo.save(copyObj(obj, dbObj));

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
	public ResponseEntity<Color> updateLastColor(@RequestBody Color obj,
			HttpServletResponse response) {

		List<Color> list = repo.getLastByDeviceId(obj.getDevice().getId());
		Color dbObj = list.get(0);
		if ((deviceRepo.findById(obj.getDevice().getId()) != null)
				&& (dbObj != null) && (obj != null)) {

			obj.setDateTime(LocalDateTime.now());
			Color objSaved = repo.save(copyObj(obj, dbObj));

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(objSaved.getId())
					.toUri();
			response.setHeader("Location", uri.toASCIIString());

			return ResponseEntity.status(HttpStatus.OK).body(objSaved);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	

	private Color copyObj(Color obj, Color dbObj) {
		if (obj.getColor() != null) {
			dbObj.setColor(obj.getColor());
		}

		if (obj.getMsg() != null) {
			dbObj.setMsg(obj.getMsg());
		}

		if (obj.getDateTime() != null) {
			dbObj.setDateTime(obj.getDateTime());
		}

		if (obj.getDesiredColor() != null) {
			dbObj.setDesiredColor(obj.getDesiredColor());
		}

		if (obj.getDesiredDateTime() != null) {
			dbObj.setDesiredDateTime(obj.getDesiredDateTime());
		}

		return dbObj;
	}
}
